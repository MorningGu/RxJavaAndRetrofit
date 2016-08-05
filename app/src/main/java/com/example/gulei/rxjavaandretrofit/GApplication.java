package com.example.gulei.rxjavaandretrofit;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

import com.example.gulei.rxjavaandretrofit.common.utils.ImageLoaderUtils;
//import com.squareup.leakcanary.LeakCanary;
import com.example.gulei.rxjavaandretrofit.common.utils.PrintUtils;
import com.example.gulei.rxjavaandretrofit.common.utils.ScreenUtil;
import com.umeng.analytics.MobclickAgent;

import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;

/**
 * Created by gulei on 2016/4/29 0029.
 */
public class GApplication extends Application {

    private static GApplication sInstance; //s的前缀，表示static m的前缀表示member

    private boolean isDebug = false;

    private Boolean hasCamera = null;

    public void onCreate() {
        super.onCreate();
        init();
    }
    private void init(){
        //内存分析工具
        // FIXME: 2016/6/29 0029  这个内存泄漏的检查，正式环境注释掉
//        LeakCanary.install(this);
        /** 设置是否对日志信息进行加密, 默认false(不加密). */
        MobclickAgent.enableEncrypt(true);
        MobclickAgent.setDebugMode( true );
        sInstance = this;
        initDebug();
        ImageLoaderUtils.INSTANCE.init(this, Bitmap.Config.RGB_565);
        //这里一定要，不然当网络请求出错时会崩溃，404必崩
//        RxJavaPlugins.getInstance().registerErrorHandler(new RxJavaErrorHandler() {
//            @Override
//            public void handleError(Throwable e) {
//                PrintUtils.e("rxJava Error",e.getMessage());
//            }
//        });
    }

    /**
     * 判断是否有相机
     * @return
     */
    public boolean hasCamera(){
        if(hasCamera==null){
            PackageManager pm = getPackageManager();
            // FEATURE_CAMERA - 后置相机
            // FEATURE_CAMERA_FRONT - 前置相机
            if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)
                    && !pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)) {
//                Log.i("camera", "non-support");
                hasCamera = false;
            } else {
//                Log.i("camera", "support");
                hasCamera = true;
            }
        }
        return hasCamera;
    }
    /**
     * 初始化是否是debug
     */
    private void initDebug(){
        ApplicationInfo appInfo = null;
        try {
            appInfo = GApplication.getInstance().getPackageManager()
                    .getApplicationInfo(GApplication.getInstance().getPackageName(),
                            PackageManager.GET_META_DATA);
            isDebug =  appInfo.metaData.getBoolean("IS_DEBUG");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 是不是debug
     * @return
     */
    public boolean isDebug(){
        return isDebug;
    }

    /**
     * 得到Application实例
     * @return
     */
    public static GApplication getInstance() {
        return sInstance;
    }

    /**
     * 获取当前系统版本号
     * @return
     */
    public int getAPIVersion() {
        int APIVersion;
        try {
            APIVersion = android.os.Build.VERSION.SDK_INT;
        } catch (NumberFormatException e) {
            APIVersion = 0;
        }
        return APIVersion;
    }
    /**
     * 获取状态栏高度
     */
    public int getStatusBarHeight() {
        return ScreenUtil.getStatusHeight(this);
    }
    /**
     * 获取的是铃声的Uri
     */
    public static Uri getDefaultRingtoneUri(Context ctx, int type) {
        return RingtoneManager.getActualDefaultRingtoneUri(ctx, type);
    }

    Vibrator vibrator;

    /**
     *  输入震动毫秒数
     */
    public void setVibrate() {
        if (vibrator == null) {
            vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        }
        vibrator.vibrate(500);
    }
    /**
     * 播放铃声
     */
    MediaPlayer mMediaPlayer;
    public MediaPlayer getMediaPlayer() {
        if (mMediaPlayer != null){
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        mMediaPlayer = MediaPlayer.create(
                this,
                getDefaultRingtoneUri(this,
                        RingtoneManager.TYPE_NOTIFICATION));
        return mMediaPlayer;
    }
    public int getMemoryCacheMaxSize() {
        ActivityManager am = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        int memoryClass = am.getLargeMemoryClass();
        return 1024 * 1024 * memoryClass / 10;
    }

}
