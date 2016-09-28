package com.example.gulei.rxjavaandretrofit.common.network;

import com.example.gulei.rxjavaandretrofit.GApplication;
import com.example.gulei.rxjavaandretrofit.common.utils.UrlInfoUtils;
import com.trello.rxlifecycle.LifecycleTransformer;

import java.util.concurrent.TimeUnit;

import cn.gulei.library.utils.LogUtils;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by gulei on 2016/9/6 0006.
 */

public enum  ApiManager {
    INSTANCE;
    private INetInterface iNetInterface;
    private Object monitor = new Object();
    /**
     * 这一部分配置常量，可以抽取出常量类
     */
    private static final long DEFAULT_TIMEOUT = 10000;//默认超时时间(毫秒)

    /**
     * 取得实例化的Retrofit
     * 可以根据不同的需求获取不同的Retrofit实例
     * @return
     */
    public INetInterface getINetInterface(){
        if (iNetInterface == null) {
            synchronized (monitor) {
                if (iNetInterface == null) {
                    //打印拦截器
                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                    logging.setLevel(GApplication.getInstance().isDebug()?HttpLoggingInterceptor.Level.BODY:HttpLoggingInterceptor.Level.NONE);
                    // 公私密匙
                    //MarvelSigningInterceptor signingInterceptor = new MarvelSigningInterceptor(KeyValue.MARVEL_PUBLIC_KEY, KeyValue.MARVEL_PRIVATE_KEY);
                    OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
                    okHttpClient.addNetworkInterceptor(new HTTPInterceptor())
                            .retryOnConnectionFailure(true)//设置出现错误进行重新连接。;
                            //.addInterceptor(signingInterceptor)//加密处理
                            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                            .readTimeout(DEFAULT_TIMEOUT,TimeUnit.MILLISECONDS)
                            .addInterceptor(logging);

                    iNetInterface = new Retrofit.Builder()
                            .client(okHttpClient.build())
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .baseUrl(UrlInfoUtils.getAppUrl())
                            .build().create(INetInterface.class);
                }
            }
        }
        return iNetInterface;
    }
    /**
     * 初始化通用的观察者
     * @param observable 观察者
     * @param resultType 请求标识
     * @param listener 请求结果的监听
     */
    public void startObservable(Observable observable, int resultType, boolean isRefresh, LifecycleTransformer former, ResultSubscriber.OnResultListener listener) {
        ResultSubscriber mSubscriber = new ResultSubscriber();
        mSubscriber.setOnResultListener(listener);
        mSubscriber.setRefresh(isRefresh);
        mSubscriber.setRequestType(resultType);
        observable.compose(former)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        LogUtils.d("取消了订阅","取消了订阅");
//                        ToastUtils.showToast("取消了订阅");
                    }
                })
                .subscribe(mSubscriber);
    }
}
