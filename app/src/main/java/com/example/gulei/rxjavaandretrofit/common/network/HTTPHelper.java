package com.example.gulei.rxjavaandretrofit.common.network;


import com.example.gulei.rxjavaandretrofit.BuildConfig;
import com.example.gulei.rxjavaandretrofit.GApplication;
import com.example.gulei.rxjavaandretrofit.common.entity.JsonResult;
import com.example.gulei.rxjavaandretrofit.common.entity.Version;
import com.example.gulei.rxjavaandretrofit.common.entity.user.UserData;
import com.example.gulei.rxjavaandretrofit.common.utils.PrintUtils;
import com.example.gulei.rxjavaandretrofit.common.utils.UrlInfoUtils;
import com.trello.rxlifecycle.LifecycleTransformer;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @Author: 盖世
 * @Data: 16/6/30
 * @Description: 网络请求数据类（单例）
 */
public enum  HTTPHelper {
    INSTANCE;
    /**
     * 这一部分配置常量，可以抽取出常量类
     */
    private static final long DEFAULT_TIMEOUT = 3000;//默认超时时间(毫秒)


    private Retrofit mRetrofit;
    private INetInterface mNetService;

     HTTPHelper() {
         //打印拦截器
         HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
         logging.setLevel(BuildConfig.DEBUG?HttpLoggingInterceptor.Level.BODY:HttpLoggingInterceptor.Level.NONE);
         // 公私密匙
         //MarvelSigningInterceptor signingInterceptor = new MarvelSigningInterceptor(KeyValue.MARVEL_PUBLIC_KEY, KeyValue.MARVEL_PRIVATE_KEY);

         OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
         okHttpClient.addNetworkInterceptor(new HTTPInterceptor())
                 .retryOnConnectionFailure(true)//设置出现错误进行重新连接。;
                 //.addInterceptor(signingInterceptor)//加密处理
                 .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                 .addInterceptor(logging);

         mRetrofit = new Retrofit.Builder()
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(UrlInfoUtils.getAppUrl())
                .build();

         mNetService = mRetrofit.create(INetInterface.class);
    }



    /**
     * 类型转换，用来统一处理返回值，通常为公共message返回字段等。具体业务这里要具体操作
     *
     * @param <T> Subscriber真正需要的数据类型，也就是返回值针对的model
     */
    private class HttpResultFunc<T> implements Func1<Object, T> {
        @Override
        public T call(Object iModel) {
            if (iModel == null) {
                try {
                    throw new Exception("result model is null");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            T t = (T) iModel;
            if (t == null) {
                try {
                    throw new Exception("cast to the model is null");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return t;
        }
    }

    /**
     * 初始化通用的观察者
     * @param observable
     * @param resultType
     * @param listener
     */
    private void initObservable(Observable observable, int resultType,boolean isRefresh, LifecycleTransformer former,ResultSubscriber.OnResultListener listener) {
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
                        PrintUtils.d("取消了订阅","取消了订阅");
//                        PrintUtils.showToast("取消了订阅");
                    }
                })
                .subscribe(mSubscriber);
    }

    //********************************对应 INetService接口中定义的请求方法*************************************************//

    /**
     * get获取网络数据的方法
     *
     * @param cityId
     */
    public <T extends JsonResult> void getWeather(Class<T> clazz , String cityId, int resultType, ResultSubscriber.OnResultListener listener) {
//        Observable<T> observable = (Observable<T>) mNetService.postLogin(cityId);
//        initObservable(observable, resultType, listener);
    }

    /**
     * 登录
     * @param phoneNum
     * @param password
     * @param resultType 用来区分请求方法的来源
     * @param listener
     */
    public void postLogin(String phoneNum, String password, int resultType, boolean isRefresh, LifecycleTransformer former,ResultSubscriber.OnResultListener listener) {
        Observable<JsonResult<UserData>> observable = mNetService.postLogin(phoneNum,password);
        initObservable(observable, resultType,isRefresh ,former,listener);
    }

    /**
     * 上传文件
     * @param body
     * @param resultType
     * @param isRefresh
     * @param former
     * @param listener
     */
    public void postUpload(RequestBody body,int resultType, boolean isRefresh,LifecycleTransformer former,ResultSubscriber.OnResultListener listener){
        Observable<JsonResult<Map<String,String>>> observable = mNetService.postUpload(body,"1","android");
        initObservable(observable, resultType,isRefresh ,former,listener);
    }

    /**
     * 检查更新
     * @param resultType
     * @param isRefresh
     * @param former
     * @param listener
     */
    public void postCheckUpdate(int resultType, boolean isRefresh,LifecycleTransformer former,ResultSubscriber.OnResultListener listener){
        Observable<JsonResult<Version>> observable = mNetService.postCheckUpdate();
        initObservable(observable, resultType,isRefresh ,former,listener);
    }

}
