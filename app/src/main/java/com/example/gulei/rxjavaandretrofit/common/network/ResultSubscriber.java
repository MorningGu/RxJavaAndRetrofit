package com.example.gulei.rxjavaandretrofit.common.network;


import com.example.gulei.rxjavaandretrofit.mvp.entity.JsonResult;

import cn.gulei.library.utils.LogUtils;
import rx.Subscriber;

/**
 * @Author: lizhipeng
 * @Data: 16/4/12 下午4:17
 * @Description: 自定义调阅者以及结果监听接口
 */
public class ResultSubscriber extends Subscriber<JsonResult> {
    private int mRequestType;
    private OnResultListener mListener = null;
    private boolean isRefresh;
    /**
     * 自定义订阅，参数用来区分网络接口，以用来在不同接口操作过程中，处理不同的逻辑
     * @param
     */
    public ResultSubscriber() {
        mListener = new OnResultListener() {
            @Override
            public void onStart(int requestType) {
            }
            @Override
            public void onCompleted(int requestType) {
            }
            @Override
            public void onError(int requestType) {
            }
            @Override
            public void onNext(JsonResult t, int requestType,boolean isRefresh) {
            }
        };
    }

    @Override
    public void onStart() {
        mListener.onStart(mRequestType);
    }

    @Override
    public void onCompleted() {
        mListener.onCompleted(mRequestType);
    }

    @Override
    public void onError(Throwable e) {
        if (e != null){
            LogUtils.e("ResultSubscriber",e.getMessage());
//            e.printStackTrace();
            mListener.onError(mRequestType);
        }
    }

    @Override
    public void onNext(JsonResult t) {
        mListener.onNext(t,mRequestType,isRefresh);
    }

    /**
     * 设置订阅监听器
     * @param listener
     */
    public void setOnResultListener(OnResultListener listener){
        if (listener != null){
            mListener = listener;
        }
    }

    /**
     * 设置请求接口类型
     * @param requestType
     */
    public void setRequestType(int requestType) {
        this.mRequestType = requestType;
    }

    /**
     * 是否刷新的标记
     * @param refresh
     */
    public void setRefresh(boolean refresh) {
        isRefresh = refresh;
    }

    /**
     * 订阅的监听器
     */
    public interface OnResultListener {
        /**
         * 网络请求订阅开始
         */
        void onStart(int requestType);
        /**
         * 网络请求完成
         */
        void onCompleted(int requestType);
        /**
         * 网络请求错误
         */
        void onError(int requestType);

        /**
         * 处理请求结果
         * @param t
         * @param requestType
         * @param isRefresh 判断是刷新还是加载
         */
        void onNext(JsonResult t, int requestType,boolean isRefresh);
    }
}
