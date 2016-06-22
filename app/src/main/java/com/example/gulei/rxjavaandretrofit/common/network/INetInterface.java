package com.example.gulei.rxjavaandretrofit.common.network;


import com.example.gulei.rxjavaandretrofit.common.entity.JsonResult;
import com.example.gulei.rxjavaandretrofit.common.entity.WeatherRequest;
import com.example.gulei.rxjavaandretrofit.common.entity.WeatherResponse;
import com.example.gulei.rxjavaandretrofit.common.entity.user.UserData;

import java.util.Map;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @Author: lizhipeng
 * @Data: 16/4/12 下午2:57
 * @Description:  网络请求接口
 */
public interface INetInterface {
    /**
     * get 请求
     * @param city
     * @return
     */
    @GET("data/cityinfo/{city_id}")
    Observable<WeatherResponse> getWeather(@Path("city_id") String city);

    /**
     * post 请求
     * @param body javabean请求体
     * @return
     */
    @POST("")
    Observable<WeatherResponse> postWeather(@Body WeatherRequest body);

    /**
     * post请求
     * @param params map请求体
     * @return
     */
    @POST("")
    Observable<WeatherResponse> postWeather2(@FieldMap Map<String, String> params);

    /**
     * 登录
     * @param phoneNum 电话号码
     * @param password 密码
     * @return
     */
    @FormUrlEncoded
    @POST("mobile/login/authentication/login.do")
    Observable<JsonResult<UserData>> postLogin(@Field("mobile") String phoneNum, @Field("password") String password);
}
