package com.example.gulei.rxjavaandretrofit.mvp.iview;

import android.app.Activity;

/**
 * Created by gulei on 2016/5/3 0003.
 */
public interface IBaseView {
    void showLoading();
    void dismissLoading();
    //状态栏
    void setStatusBarColor(int color);
    //title背景颜色
    void setHeaderColor(int color);

    /**
     * 显示更新的弹窗
     * @param isForce 是否为强制更新
     * @param url 下载地址
     */
    void showUpdateDialog(boolean isForce,String url);

}
