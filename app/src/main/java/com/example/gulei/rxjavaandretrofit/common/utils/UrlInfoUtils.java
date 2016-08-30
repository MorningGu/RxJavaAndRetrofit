package com.example.gulei.rxjavaandretrofit.common.utils;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.example.gulei.rxjavaandretrofit.BuildConfig;
import com.example.gulei.rxjavaandretrofit.GApplication;
import com.example.gulei.rxjavaandretrofit.R;


public class UrlInfoUtils {
    private static String appUrl;
//    private static String appUrl = "http://192.168.18.53:8080/";
    private static String userUrl;
    private static String shopUrl;
    private static String contentUrl;
    private static String activityUrl;
    private static String imUrl;
    private static String mUrl;
    private static String mHtml;
    public static int server_port = 5222;
    public static String server_name = "服务器地址.cn";
    private static String urlCode;

    /**
     * 原来app的
     * @return
     */
    public static String getAppUrl() {
        if(TextUtils.isEmpty(appUrl)){
            if(GApplication.getInstance().isDebug()){
                setAppUrl(GApplication.getInstance().getApplicationContext().getString(R.string.debug_app_url)+UrlInfoUtils.getUrlCode());
            }else{
                setAppUrl(GApplication.getInstance().getApplicationContext().getString(R.string.release_app_url)+UrlInfoUtils.getUrlCode());
            }
        }
        return appUrl;
    }

//    /**
//     * 用户中心
//     * @return
//     */
//    public static String getUserUrl() {
//        if(TextUtils.isEmpty(userUrl)){
//            if(UrlInfoUtils.getRelease()){
//                setUserUrl(GApplication.getInstance().getApplicationContext().getString(R.string.release_user_url)+UrlInfoUtils.getUrlCode());
//            }else{
//                setUserUrl(GApplication.getInstance().getApplicationContext().getString(R.string.debug_user_url)+UrlInfoUtils.getUrlCode());
//            }
//        }
//        return userUrl;
//    }

//    /**
//     * 商城
//     * @return
//     */
//    public static String getShopUrl() {
//        if(TextUtils.isEmpty(shopUrl)){
//            if(UrlInfoUtils.getRelease()){
//                setShopUrl(GApplication.getInstance().getApplicationContext().getString(R.string.release_shop_url)+UrlInfoUtils.getUrlCode());
//            }else{
//                setShopUrl(GApplication.getInstance().getApplicationContext().getString(R.string.debug_shop_url)+UrlInfoUtils.getUrlCode());
//            }
//        }
//        return shopUrl;
//    }

//    /**
//     * 内容中心
//     * @return
//     */
//    public static String getContentUrl() {
//        if(TextUtils.isEmpty(contentUrl)){
//            if(UrlInfoUtils.getRelease()){
//                setContentUrl(GApplication.getInstance().getApplicationContext().getString(R.string.release_content_url)+UrlInfoUtils.getUrlCode());
//            }else{
//                setContentUrl(GApplication.getInstance().getApplicationContext().getString(R.string.debug_content_url)+UrlInfoUtils.getUrlCode());
//            }
//        }
//        return contentUrl;
//    }

//    /**
//     * 活动中心
//     * @return
//     */
//    public static String getActivityUrl() {
//        if(TextUtils.isEmpty(activityUrl)){
//            if(UrlInfoUtils.getRelease()){
//                setActivityUrl(GApplication.getInstance().getApplicationContext().getString(R.string.release_activity_url)+UrlInfoUtils.getUrlCode());
//            }else{
//                setActivityUrl(GApplication.getInstance().getApplicationContext().getString(R.string.debug_activity_url)+UrlInfoUtils.getUrlCode());
//            }
//        }
//        return activityUrl;
//    }

//    /**
//     * im
//     * @return
//     */
//    public static String getImUrl() {
//        if(TextUtils.isEmpty(imUrl)){
//            if(UrlInfoUtils.getRelease()){
//                setImUrl(GApplication.getInstance().getApplicationContext().getString(R.string.release_im_url));
//            }else{
//                setImUrl(GApplication.getInstance().getApplicationContext().getString(R.string.debug_im_url));
//            }
//        }
//        return imUrl;
//    }


    private static String getUrlCode() {
        if(TextUtils.isEmpty(UrlInfoUtils.urlCode)){
            try {
                ApplicationInfo appInfo = GApplication.getInstance().getPackageManager()
                        .getApplicationInfo(GApplication.getInstance().getPackageName(),
                                PackageManager.GET_META_DATA);
                setUrlCode(appInfo.metaData.getString("URL_CODE"));
            } catch (Exception e) {

            }
        }
        return urlCode;
    }

//    /**
//     * web 页面
//     * @return
//     */
//    public static String getmHtml() {
//        if(TextUtils.isEmpty(mHtml)){
//            if(UrlInfoUtils.getRelease()){
//                setmHtml(GApplication.getInstance().getApplicationContext().getString(R.string.release_h5_url));
//            }else{
//                setmHtml(GApplication.getInstance().getApplicationContext().getString(R.string.debug_h5_url));
//            }
//        }
//        return mHtml;
//    }

    private static void setUrlCode(String urlCode) {
        UrlInfoUtils.urlCode = urlCode;
    }
    public static void setAppUrl(String appUrl) {
        UrlInfoUtils.appUrl = appUrl;
    }

    public static void setUserUrl(String userUrl) {
        UrlInfoUtils.userUrl = userUrl;
    }

    public static void setShopUrl(String shopUrl) {
        UrlInfoUtils.shopUrl = shopUrl;
    }

    public static void setContentUrl(String contentUrl) {
        UrlInfoUtils.contentUrl = contentUrl;
    }

    public static void setActivityUrl(String activityUrl) {
        UrlInfoUtils.activityUrl = activityUrl;
    }

    public static void setImUrl(String imUrl) {
        UrlInfoUtils.imUrl = imUrl;
    }

    public static void setmUrl(String mUrl) {
        UrlInfoUtils.mUrl = mUrl;
    }

    public static void setmHtml(String mHtml) {
        UrlInfoUtils.mHtml = mHtml;
    }
}
