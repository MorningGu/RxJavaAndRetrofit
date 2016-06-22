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

import com.example.gulei.rxjavaandretrofit.common.utils.DeviceUuidFactory;

import com.example.gulei.rxjavaandretrofit.common.utils.ImageLoaderUtils;
import com.squareup.leakcanary.LeakCanary;
import com.umeng.analytics.MobclickAgent;

import java.lang.reflect.Field;

/**
 * Created by gulei on 2016/4/29 0029.
 */
public class GApplication extends Application {

    private static GApplication mInstance;
    //设备id工厂
    private DeviceUuidFactory deviceUuidFactory;

    private boolean isRelease = false;

    private Boolean hasCamera = null;
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }
    private void init(){
        //内存分析工具
        LeakCanary.install(this);
        /** 设置是否对日志信息进行加密, 默认false(不加密). */
        MobclickAgent.enableEncrypt(true);
        mInstance = this;
        deviceUuidFactory = new DeviceUuidFactory(this.getApplicationContext());
        initDebug();
        ImageLoaderUtils.INSTANCE.init(this, Bitmap.Config.RGB_565);
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
            isRelease =  appInfo.metaData.getBoolean("IS_RELEASE");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 是不是debug
     * @return
     */
    public boolean isRelease(){
        return isRelease;
    }

    /**
     * 得到Application实例
     * @return
     */
    public static GApplication getInstance() {
        return mInstance;
    }
    /**
     * 获取设备号
     *
     * @return
     */
    public String getDeviceId() {
        return deviceUuidFactory.getDeviceUuid();
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
    public int getBarHeight() {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 38;// 默认为38，貌似大部分是这样的

        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = getResources().getDimensionPixelSize(x);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
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
