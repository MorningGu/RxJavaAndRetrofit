package com.example.gulei.rxjavaandretrofit.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gulei.rxjavaandretrofit.R;
import com.example.gulei.rxjavaandretrofit.mvp.iview.IUpdateActivityView;
import com.example.gulei.rxjavaandretrofit.mvp.presenter.UpdateActivityPresenter;
import com.example.gulei.rxjavaandretrofit.ui.base.BaseActivity;
import com.trello.rxlifecycle.ActivityEvent;

public class UpdateActivity extends BaseActivity implements IUpdateActivityView {
    Button btn_update;
    TextView tv_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initView();
        presenter = new UpdateActivityPresenter(this);

    }
    private void initView(){
        btn_update = (Button)findViewById(R.id.btn_update);
        tv_data = (TextView)findViewById(R.id.tv_data);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((UpdateActivityPresenter)presenter).checkUpdate("sss",bindUntilEvent(ActivityEvent.DESTROY));
            }
        });
    }

    @Override
    public void updateData(String text) {
        tv_data.setText(text);
    }
}
