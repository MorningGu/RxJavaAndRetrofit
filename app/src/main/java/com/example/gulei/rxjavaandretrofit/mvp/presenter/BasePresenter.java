package com.example.gulei.rxjavaandretrofit.mvp.presenter;

import android.os.Environment;

import com.example.gulei.rxjavaandretrofit.GApplication;
import com.example.gulei.rxjavaandretrofit.common.entity.JsonResult;
import com.example.gulei.rxjavaandretrofit.common.entity.enums.NetCodeNormal;
import com.example.gulei.rxjavaandretrofit.common.network.HTTPHelper;
import com.example.gulei.rxjavaandretrofit.common.network.INetInterface;
import com.example.gulei.rxjavaandretrofit.common.network.ResultSubscriber;
import com.example.gulei.rxjavaandretrofit.common.utils.PrintUtils;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;


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

    /**
     * 下载apk
     * @param url
     * @param path
     * @param name
     */
    public void downloadAPK(String url, final String path, final String name){
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(GApplication.getInstance().isRelease()?HttpLoggingInterceptor.Level.NONE:HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder().addInterceptor(logging);
        Retrofit retrofit = retrofitBuilder
                .client(okHttpClient.build())
                .baseUrl(UrlInfoUtils.getAppUrl())
                .build();
        INetInterface mNetService = retrofit.create(INetInterface.class);
        Call<ResponseBody> call = mNetService.downloadAPK(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    InputStream is = response.body().byteStream();
                    File filePath = new File(path);
                    if(!filePath.exists()){
                        filePath.mkdirs();
                    }
                    File file = new File(path, name);
                    FileOutputStream fos = new FileOutputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(is);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = bis.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                        fos.flush();
                    }
                    fos.close();
                    bis.close();
                    is.close();
                    mView.installApk(path,name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}
