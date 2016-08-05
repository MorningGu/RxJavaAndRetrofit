package com.example.gulei.rxjavaandretrofit.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private Button btn_send;
    private Button btn_receive;
    private EditText et_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        initDefaultHeader("网络通信");
        presenter = new NetActivityPresenter(this);
        initView();
    }
    private void initView(){
        tv_data = (TextView) findViewById(R.id.tv_data);
        btn_start = (Button)findViewById(R.id.btn_start);
        btn_upload = (Button)findViewById(R.id.btn_upload);
        btn_send = (Button)findViewById(R.id.btn_send);
        btn_receive = (Button)findViewById(R.id.btn_receive);
        et_content = (EditText) findViewById(R.id.et_content);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = et_content.getText().toString();
                tv_data.setText(msg);
//                ((NetActivityPresenter)presenter).sendMessage(Base64.encodeToString(msg.getBytes(),Base64.DEFAULT),bindUntilEvent(ActivityEvent.DESTROY));
                //这里的bindUntilEvent(ActivityEvent.DESTROY)是表示在什么样的生命周期取消订阅
                ((NetActivityPresenter)presenter).sendMessage(msg,bindUntilEvent(ActivityEvent.DESTROY));
            }
        });
        btn_receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //这里的bindUntilEvent(ActivityEvent.DESTROY)是表示在什么样的生命周期取消订阅
                ((NetActivityPresenter)presenter).requestMessage(bindUntilEvent(ActivityEvent.DESTROY));
            }
        });

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里的bindUntilEvent(ActivityEvent.DESTROY)是表示在什么样的生命周期取消订阅
                ((NetActivityPresenter)presenter).method(null,bindUntilEvent(ActivityEvent.DESTROY));
            }
        });
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,123)){
                    ((NetActivityPresenter)presenter).upload("ddd",true,bindUntilEvent(ActivityEvent.DESTROY));
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
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    ((NetActivityPresenter)presenter).upload("ddd",true,bindUntilEvent(ActivityEvent.DESTROY));
                } else {
                    // Permission Denied
                   PrintUtils.showToast("没有磁盘读写权限");
                }
                break;
        }
    }
}
