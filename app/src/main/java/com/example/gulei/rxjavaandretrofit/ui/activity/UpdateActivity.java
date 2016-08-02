package com.example.gulei.rxjavaandretrofit.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gulei.rxjavaandretrofit.R;
import com.example.gulei.rxjavaandretrofit.common.utils.PrintUtils;
import com.example.gulei.rxjavaandretrofit.mvp.iview.IUpdateActivityView;
import com.example.gulei.rxjavaandretrofit.mvp.presenter.NetActivityPresenter;
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
        initDefaultHeader("检查更新");
        initView();
        presenter = new UpdateActivityPresenter(this);

    }
    private void initView(){
        btn_update = (Button)findViewById(R.id.btn_update);
        tv_data = (TextView)findViewById(R.id.tv_data);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,123)){
                    ((UpdateActivityPresenter)presenter).checkUpdate("sss",bindUntilEvent(ActivityEvent.DESTROY));
                }
            }
        });
    }

    @Override
    public void updateData(String text) {
        tv_data.setText(text);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 123:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    ((UpdateActivityPresenter)presenter).checkUpdate("sss",bindUntilEvent(ActivityEvent.DESTROY));
                } else {
                    // Permission Denied
                    PrintUtils.showToast("没有磁盘读写权限");
                }
                break;
        }
    }
}
