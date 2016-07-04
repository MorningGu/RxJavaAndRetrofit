package com.example.gulei.rxjavaandretrofit.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gulei.rxjavaandretrofit.R;
import com.example.gulei.rxjavaandretrofit.mvp.iview.INetActivityView;
import com.example.gulei.rxjavaandretrofit.mvp.presenter.NetActivityPresenter;
import com.example.gulei.rxjavaandretrofit.ui.base.BaseActivity;

public class NetActivity extends BaseActivity implements INetActivityView{
    private TextView tv_data;
    private Button btn_start;
    private NetActivityPresenter presenter = new NetActivityPresenter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        initView();
    }
    private void initView(){
        tv_data = (TextView) findViewById(R.id.tv_data);
        btn_start = (Button)findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.method(null);
            }
        });
    }
    @Override
    public void updateData(String text) {
        tv_data.setText(text);
    }

}
