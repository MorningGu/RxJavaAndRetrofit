package com.example.gulei.rxjavaandretrofit.mvp.presenter;


import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.example.gulei.rxjavaandretrofit.BuildConfig;
import com.example.gulei.rxjavaandretrofit.common.entity.JsonResult;
import com.example.gulei.rxjavaandretrofit.common.entity.enums.NetCodeNormal;
import com.example.gulei.rxjavaandretrofit.common.network.INetInterface;
import com.example.gulei.rxjavaandretrofit.common.network.ResultSubscriber;
import com.example.gulei.rxjavaandretrofit.common.utils.AppManager;
import com.example.gulei.rxjavaandretrofit.common.utils.PrintUtils;
import com.example.gulei.rxjavaandretrofit.common.utils.ThreadPoolUtil;
import com.example.gulei.rxjavaandretrofit.common.utils.UrlInfoUtils;
import com.example.gulei.rxjavaandretrofit.mvp.iview.IBaseView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.BufferedSink;
import okio.Okio;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


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
        if(NetCodeNormal.UPDATE_FORCE.getCode() == t.getReturnCode()){
            //强制更新
            PrintUtils.showToast(t.getMsg());
            mView.showUpdateDialog(true,"下载地址");
        }else if(NetCodeNormal.LOGIN_TIME_OUT.getCode() == t.getReturnCode()){
            PrintUtils.showToast(t.getMsg());
        } else if(NetCodeNormal.SUCCESS.getCode() != t.getReturnCode()){
            PrintUtils.showToast(t.getMsg());
        }
    }

}
