package com.example.gulei.rxjavaandretrofit.mvp.iview;

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
}
