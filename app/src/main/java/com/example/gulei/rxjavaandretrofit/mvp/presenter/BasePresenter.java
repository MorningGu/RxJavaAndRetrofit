package com.example.gulei.rxjavaandretrofit.mvp.presenter;

import com.example.gulei.rxjavaandretrofit.common.entity.JsonResult;
import com.example.gulei.rxjavaandretrofit.common.entity.enums.NetCodeNormal;
import com.example.gulei.rxjavaandretrofit.common.network.ResultSubscriber;
import com.example.gulei.rxjavaandretrofit.common.utils.PrintUtils;
import com.example.gulei.rxjavaandretrofit.mvp.iview.IBaseView;


/**
 * Created by gulei on 2016/5/3 0003.
 */
public class BasePresenter implements ResultSubscriber.OnResultListener {
    private IBaseView mView;
    public BasePresenter(IBaseView view){
        mView = view;
    }
    @Override
    public void onStart(int requestType) {
        mView.showLoading();
    }

    @Override
    public void onCompleted(int requestType) {
        mView.dismissLoading();
    }

    @Override
    public void onError(int requestType) {
        mView.dismissLoading();
        PrintUtils.showToast("网络请求请求失败");
    }

    @Override
    public void onNext(JsonResult t, int requestType,boolean isRefresh) {
        //子类进行个性化实现,这里对错误的code进行统一处理
        if(NetCodeNormal.UPDATE_MAST.getCode() == t.getReturnCode()){
            PrintUtils.showToast(t.getMsg());
        }else if(NetCodeNormal.LOGIN_TIME_OUT.getCode() == t.getReturnCode()){
            PrintUtils.showToast(t.getMsg());
        } else if(NetCodeNormal.SUCCESS.getCode() != t.getReturnCode()){
            PrintUtils.showToast(t.getMsg());
        }
    }
}
