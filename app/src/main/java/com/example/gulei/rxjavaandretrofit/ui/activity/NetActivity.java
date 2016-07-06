package com.example.gulei.rxjavaandretrofit.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gulei.rxjavaandretrofit.R;
import com.example.gulei.rxjavaandretrofit.common.utils.PrintUtils;
import com.example.gulei.rxjavaandretrofit.mvp.iview.INetActivityView;
import com.example.gulei.rxjavaandretrofit.mvp.presenter.NetActivityPresenter;
import com.example.gulei.rxjavaandretrofit.ui.base.BaseActivity;
import com.trello.rxlifecycle.ActivityEvent;

public class NetActivity extends BaseActivity implements INetActivityView{
    private TextView tv_data;
    private Button btn_start;
    private Button btn_upload;
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
        btn_upload = (Button)findViewById(R.id.btn_upload);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里的bindUntilEvent(ActivityEvent.DESTROY)是表示在什么样的生命周期取消订阅
                presenter.method(null,bindUntilEvent(ActivityEvent.DESTROY));
            }
        });
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,123)){
                    presenter.upload("ddd",true,bindUntilEvent(ActivityEvent.DESTROY));
                }
            }
        });
    }
    @Override
    public void updateData(String text) {
        tv_data.setText(text);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PrintUtils.d("NetActivity","销毁了activity");
    }

    @Override
    public void onPause() {
        super.onPause();
        PrintUtils.d("NetActivity","activity暂停");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 123:
                presenter.upload("ddd",true,bindUntilEvent(ActivityEvent.DESTROY));
                break;
        }
    }
}
