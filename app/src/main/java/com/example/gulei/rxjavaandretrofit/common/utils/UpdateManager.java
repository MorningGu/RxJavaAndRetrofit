package com.example.gulei.rxjavaandretrofit.common.utils;

import android.content.Context;
import android.util.Log;

import com.example.gulei.rxjavaandretrofit.BuildConfig;
import com.example.gulei.rxjavaandretrofit.common.network.INetInterface;
import com.example.gulei.rxjavaandretrofit.common.network.ProgressInterceptor;
import com.example.gulei.rxjavaandretrofit.common.network.ProgressListener;

import java.io.File;
import java.io.IOException;

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
 * Created by gulei on 2016/8/1 0001.
 */
public enum UpdateManager {
    INATANCE;

    /**
     * 比较版本号 是否需要更新
     * @param versionCode 本地版本号
     * @param context 上下文
     * @return true 需要更新 false 不需要更新
     */
    public boolean checkUpdate(int versionCode, Context context){
        if(versionCode > AppUtil.getVersionCode(context)){
            return true;
        }
        return false;
    }
    /**
     * 下载apk
     * @param url
     * @param path
     * @param name
     */
    public void downloadAPK(String url, final String path, final String name,ProgressListener progressListener){
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG?HttpLoggingInterceptor.Level.BODY:HttpLoggingInterceptor.Level.NONE);
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(new ProgressInterceptor(progressListener));
        Retrofit retrofit = retrofitBuilder
                .client(okHttpClient.build())
                .baseUrl(UrlInfoUtils.getAppUrl())

                .build();
        INetInterface mNetService = retrofit.create(INetInterface.class);
        Call<ResponseBody> call = mNetService.downloadAPK(url);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    PrintUtils.d("---------------------","请求到数据");
                    BufferedSink sink = null;
                    //下载文件到本地
                    File filePath = new File(path);
                    if(!filePath.exists()){
                        filePath.mkdirs();
                    }
                    File file = new File(path, name);
                    try {
                        sink = Okio.buffer(Okio.sink(file));
                        sink.writeAll(response.body().source());
                    } catch(Exception e) {
                        PrintUtils.d("---------------------","异常了");
                        e.printStackTrace();
                    } finally {
                        try {
                            if(sink != null) sink.close();
                        } catch(IOException e) {
                            PrintUtils.d("---------------------","异常了2");
                            e.printStackTrace();
                        }

                    }
                    Log.d("下载成功", "isSuccessful");
                } else {
                    Log.d("---------------------", response.code() + "");
                }
//                ThreadPoolUtil.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        InputStream is = null;
//                        BufferedInputStream bis = null;
//                        FileOutputStream fos = null;
//                        try {
//                            is = response.body().byteStream();
//                            File filePath = new File(path);
//                            if(!filePath.exists()){
//                                filePath.mkdirs();
//                            }
//                            File file = new File(path, name);
//                            fos = new FileOutputStream(file);
//                            bis = new BufferedInputStream(is);
//                            byte[] buffer = new byte[4096];
//                            int len;
//                            while ((len = bis.read(buffer)) != -1) {
//                                fos.write(buffer, 0, len);
//                                fos.flush();
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }finally {
//                            try {
//                                if(fos!=null)
//                                    fos.close();
//                                if(bis!=null)
//                                    bis.close();
//                                if(is!=null)
//                                    is.close();
//                                AppManager.INSTANCE.currentActivity().runOnUiThread(new Runnable() {
//                                    public void run() {
//                                        mView.installApk(path,name);
//                                    }
//                                });
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                });
//                PrintUtils.showToast("服务端返回结果：");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                PrintUtils.d("---------------------","异常了3");
            }
        });
    }
}
