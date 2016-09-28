package com.example.gulei.rxjavaandretrofit.mvp.presenter;


import com.example.gulei.rxjavaandretrofit.mvp.entity.JsonResult;
import com.example.gulei.rxjavaandretrofit.mvp.entity.enums.NetCodeNormal;
import com.example.gulei.rxjavaandretrofit.common.network.ResultSubscriber;

import com.example.gulei.rxjavaandretrofit.common.utils.ToastUtils;
import com.example.gulei.rxjavaandretrofit.mvp.iview.IBaseView;

import java.lang.ref.WeakReference;


/**
 * Created by gulei on 2016/5/3 0003.
 */
public abstract class BasePresenter<T> implements ResultSubscriber.OnResultListener {
    protected WeakReference<T> mViewReference;
    /**
     * Presenter与View关联
     * @param view
     */
    public void attachView(T view){
        mViewReference = new WeakReference<T>(view);
    }

    /**
     * Presenter与View解除关联
     */
    public void detachView(){
        if(mViewReference != null){
            mViewReference.clear();
            mViewReference = null;
        }
    }

    protected T getView(){
        if(mViewReference != null){
            return mViewReference.get();
        }
        return null;
    }

    /**
     * Presenter与View是否已关联
     * @return
     */
    public boolean isViewAttached(){
        return mViewReference != null && mViewReference.get() != null;
    }

    @Override
    public void onStart(int requestType) {

    }

    @Override
    public void onCompleted(int requestType) {

    }

    @Override
    public void onError(int requestType) {
        IBaseView view = (IBaseView) getView();
        if(view!=null){
            view.dismissLoading();
        }
        ToastUtils.showToast("网络请求请求失败");
    }

    @Override
    public void onNext(JsonResult t, int requestType,boolean isRefresh) {
        //子类进行个性化实现,这里对错误的code进行统一处理
        if(NetCodeNormal.UPDATE_FORCE.getCode() == t.getReturnCode()){
            //强制更新
            ToastUtils.showToast(t.getMsg());
            IBaseView view = (IBaseView) getView();
            if(view!=null){
                view.showUpdateDialog(true,"下载地址");
            }
        }else if(NetCodeNormal.LOGIN_TIME_OUT.getCode() == t.getReturnCode()){
            ToastUtils.showToast(t.getMsg());
        } else if(NetCodeNormal.SUCCESS.getCode() != t.getReturnCode()){
            ToastUtils.showToast(t.getMsg());
        }
    }

}
