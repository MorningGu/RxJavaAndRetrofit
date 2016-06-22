package com.example.gulei.rxjavaandretrofit.common.network;

/**
 * Created by Administrator on 2016/3/21.
 */

import com.example.gulei.rxjavaandretrofit.common.utils.PrintUtils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 添加Key和密码 * <p> * Created by wangchenlong on 16/1/21.
 */
public class MarvelSigningInterceptor implements Interceptor {
    private static String TAG = "MarvelSigningInterceptor";
    private final String mApiKey;
    private final String mApiSecret;
    public static final String PARAM_API_KEY = "test1";
    public static final String PARAM_TIMESTAMP = "test2";
    public static final String PARAM_HASH = "test3";
    public MarvelSigningInterceptor(String apiKey, String apiSecret) {
        mApiKey = apiKey;
        mApiSecret = apiSecret;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String marvelHash = generateMarvelHash(mApiKey, mApiSecret);
        Request oldRequest = chain.request();

        // 添加新的参数
        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host())
                .addQueryParameter(PARAM_API_KEY, mApiKey)
                .addQueryParameter(PARAM_TIMESTAMP, getUnixTimeStamp())
                .addQueryParameter(PARAM_HASH, marvelHash);

        // 新的请求
        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(authorizedUrlBuilder.build())
                .build();
        PrintUtils.i(TAG, "request:" + newRequest.toString());
        Response response = chain.proceed(newRequest);
        PrintUtils.i(TAG, "response body:" + response.toString());
        return response;
    }
    public String generateMarvelHash(String str1, String str2) {
        return str1 + str2;
    }

    public String getUnixTimeStamp() {
        return "test";
    }
}
