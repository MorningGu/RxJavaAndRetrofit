package com.example.gulei.rxjavaandretrofit.common.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.gulei.rxjavaandretrofit.BuildConfig;
import com.example.gulei.rxjavaandretrofit.GApplication;

/**
 * 输出工具类
 * Created by gulei on 2016/5/23 0023.
 */
public class PrintUtils {

    public static int v(String tag, String msg) {
        if(BuildConfig.DEBUG){
            return Log.v(tag, msg);
        }
        return 0;
    }

    public static int d(String tag, String msg) {
        if(BuildConfig.DEBUG){
            return Log.d(tag, msg);
        }
        return 0;
    }


    public static int i(String tag, String msg) {
        if(BuildConfig.DEBUG){
            return Log.i(tag, msg);
        }
        return 0;
    }


    public static int w(String tag, String msg) {
        if(BuildConfig.DEBUG){
            return Log.w(tag, msg);
        }
        return 0;
    }


    public static int e(String tag, String msg) {
        if(BuildConfig.DEBUG){
            return Log.e(tag, msg);
        }
        return 0;
    }
    //吐司部分，只显示一个
    private static Toast mToast;
    public static void showToast(String text){
        showToast(GApplication.getInstance(),text,Toast.LENGTH_LONG);
    }
    public static void showToast (Context context, String text, int duration) {
        if(TextUtils.isEmpty(text)){
            return;
        }
//        Toast.makeText(context, text, duration).show();
        if(mToast == null){
            mToast = Toast.makeText(context, text, duration);
        }else{
            mToast.setDuration(duration);
            mToast.setText(text);
        }
        mToast.show();
    }

    public static void showToast (Context context, int strId, int duration) {
        showToast (context, context.getString(strId), duration);
    }

    /**
     * 获取一个非null的字符串
     * @param text
     * @return
     */
    public static String getText(String text){
        if(text==null){
            return "";
        }
        return text;
    }
}
