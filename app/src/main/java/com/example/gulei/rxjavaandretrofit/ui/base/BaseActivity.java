package com.example.gulei.rxjavaandretrofit.ui.base;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.example.gulei.rxjavaandretrofit.R;
import com.example.gulei.rxjavaandretrofit.common.utils.AppManager;
import com.example.gulei.rxjavaandretrofit.common.utils.LoadingUtils;
import com.example.gulei.rxjavaandretrofit.mvp.iview.IBaseView;
import com.example.gulei.rxjavaandretrofit.ui.view.HeadLayout;
import com.example.gulei.rxjavaandretrofit.ui.view.statusbar.StatusBarHelper;
import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.components.RxActivity;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by gulei on 2016/3/10.
 */
public abstract class BaseActivity extends RxAppCompatActivity implements IBaseView {
    public int mScreenWidth;
    public int mScreenHeight;
    //titlebar
    private HeadLayout mHeadLayout;
    //状态栏
    protected StatusBarHelper mStatusBarHelper;
    LoadingUtils mLoadingUtil;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化屏幕区域
        AppManager.getAppManager().addActivity(this);
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        mScreenWidth = metric.widthPixels;
        mScreenHeight = metric.heightPixels;
    }

    public void onResume() {
        super.onResume();
        //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。参数为页面名称，可自定义)
        MobclickAgent.onPageStart(this.getClass().getName());
        //基本的统计
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。参数为页面名称，可自定义
        MobclickAgent.onPageEnd(this.getClass().getName());
        //统计时长
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        AppManager.getAppManager().finishActivity(this);
//    }

    /**
     * 默认的导航栏，左边图片和title
     * @param title
     */
    protected void initDefaultHeader(String title ){
        mHeadLayout = (HeadLayout)findViewById(R.id.common_header);
        if(mHeadLayout!=null){
            mHeadLayout.initView();
            mHeadLayout.initTitleAndLeftImage(title, R.mipmap.ic_launcher, new HeadLayout.OnLeftClickListener() {
                @Override
                public void onClick() {
                    AppManager.getAppManager().finishActivity(BaseActivity.this);
                }
            });
        }
        initTitleColor(R.color.colorPrimary);
    }
    /**
     * 导航栏，左边图片和title右边文字
     * @param title
     */
    protected void initTitleAndRightText(String title, String rightText, HeadLayout.OnRightClickListener onRightClickListener){
        mHeadLayout = (HeadLayout)findViewById(R.id.common_header);
        if(mHeadLayout!=null){
            mHeadLayout.initView();
            mHeadLayout.initTitleAndImageText(title, R.mipmap.ic_launcher, rightText,new HeadLayout.OnLeftClickListener() {
                @Override
                public void onClick() {
                    AppManager.getAppManager().finishActivity(BaseActivity.this);
                }
            },onRightClickListener);
        }
    }
    /**
     * 导航栏，左边图片和title右边文字
     * @param title
     */
    protected void initTitleAndRightText(String title, String rightText, HeadLayout.OnLeftClickListener onLeftClickListener, HeadLayout.OnRightClickListener onRightClickListener){
        mHeadLayout = (HeadLayout)findViewById(R.id.common_header);
        if(mHeadLayout!=null){
            mHeadLayout.initView();
            mHeadLayout.initTitleAndImageText(title, R.mipmap.ic_launcher, rightText,onLeftClickListener,onRightClickListener);
        }
    }
    @Override
    public void showLoading() {
        if (mLoadingUtil == null) {
            mLoadingUtil = new LoadingUtils(this, true);
        }
        mLoadingUtil.showLoading();
    }

    @Override
    public void dismissLoading() {
        if (mLoadingUtil != null) {
            mLoadingUtil.dismissLoading();
        }
    }
    @Override
    public void setStatusBarColor(int color) {
        if (mStatusBarHelper == null) {
            mStatusBarHelper = new StatusBarHelper(this, StatusBarHelper.LEVEL_19_TRANSLUCENT,
                    StatusBarHelper.LEVEL_21_VIEW);
        }
        mStatusBarHelper.setColor(getResources().getColor(color));
    }

    @Override
    public void setHeaderColor(int color) {
        mHeadLayout.setBackgroundColor(getResources().getColor(color));
    }
    private void initTitleColor(int color){
        setStatusBarColor(color);
        setHeaderColor(color);
    }

    /**
     * 检查权限
     * @param permission
     * @return
     */
    public boolean checkPermission(String permission){
        int checkCallPhonePermission = ContextCompat.checkSelfPermission(this, permission);
        if(checkCallPhonePermission != PackageManager.PERMISSION_GRANTED){
            return false;
        }else{
           return true;
        }
    }

    /**
     * 请求权限
     * 6.0之后版本的才有用，不然都返回true，onRequestPermissionsResult在面外自己实现
     * @param permission 权限名称 Manifest.permission.WRITE_EXTERNAL_STORAGE
     * @param requestCode onRequestPermissionsResult中的requestCode参数值，用来区分请求的权限
     */
    public boolean requestPermission(String permission,int requestCode){
        if (Build.VERSION.SDK_INT >= 23) {
            if(!checkPermission(permission)){
                ActivityCompat.requestPermissions(this,new String[]{permission},requestCode);
                return false;
            }
        }
        return true;
    }
}
