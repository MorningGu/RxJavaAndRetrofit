package com.example.gulei.rxjavaandretrofit;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

import com.example.gulei.rxjavaandretrofit.common.network.ProgressListener;
import com.example.gulei.rxjavaandretrofit.common.utils.PrintUtils;
import com.example.gulei.rxjavaandretrofit.common.utils.UpdateManager;

import java.io.File;

/**
 * Created by gulei on 2016/8/1 0001.
 * 版本更新的策略
 * 首先由MainActivity或者启动页startService，stopService则应放到MainActivity的onDestroy方法里面
 * 在检查到有新版本，需要更新时，在相应的activity中进行bindService操作，然后在相应activity的onDestroy方法中进行unBindService操作
 */
public class GService extends Service {
    GBinder mGBinder = new GBinder();
    /** Notification管理 */
    public NotificationManager mNotificationManager;
    NotificationCompat.Builder mBuilder;
    // 通知栏跳转Intent
    private Intent updateIntent = null;
    private PendingIntent updatePendingIntent = null;
    /** Notification的ID 下载的通知id*/
    int notifyId = 102;
    AppReceiver appReceiver = null;
    @Override
    public void onCreate() {
        super.onCreate();
        initService();
    }

    @Override
    public void onDestroy() {
        if(appReceiver!=null){
            unregisterReceiver(appReceiver);
            appReceiver = null;
        }
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mGBinder;
    }
    public class GBinder extends Binder{
        public void startDownLoadAPK(String url, final String path, final String name){
            showProgressNotify(false,0);
            UpdateManager.INATANCE.downloadAPK(url, path, name, new ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    if(done){
                        installApk(path,name);
                        setNotifyInstall();
                        registerInstallReceiver();
                        return;
                    }
                    setNotify((int)(100 * bytesRead / contentLength));
                }
            });
        }
    }
    /**
     * 初始化要用到的系统服务
     */
    private void initService() {
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        initNotification();
    }

    /**
     * 注册安装广播
     */
    private void registerInstallReceiver(){
        if(appReceiver == null){
            appReceiver = new AppReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_PACKAGE_ADDED);
            filter.addAction(Intent.ACTION_PACKAGE_REPLACED);
            filter.addDataScheme("package");//这个加上才能接收到广播
            registerReceiver(appReceiver,filter);
        }
    }
    //初始化对话框
    private void initNotification() {
        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setWhen(System.currentTimeMillis())// 通知产生的时间，会在通知信息里显示
                .setContentIntent(getDefaultIntent(Notification.FLAG_AUTO_CANCEL,null))
                // .setNumber(number)//显示数量
                .setPriority(Notification.PRIORITY_DEFAULT)// 设置该通知优先级
                .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消,不设置的话，只靠Notification.FLAG_AUTO_CANCEL是不会有这个效果的
                .setOngoing(false)// ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE)// 向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：
                // Notification.DEFAULT_ALL Notification.DEFAULT_SOUND 添加声音 //
                // requires VIBRATE permission
                .setSmallIcon(R.mipmap.ic_launcher);
    }
    /**
     * @获取默认的pendingIntent,为了防止2.3及以下版本报错
     * @flags属性:
     * 在顶部常驻:Notification.FLAG_ONGOING_EVENT
     * 点击去除： Notification.FLAG_AUTO_CANCEL
     */
    public PendingIntent getDefaultIntent(int flags,Intent intent){
        if(intent == null){
            intent = new Intent();
        }
        PendingIntent pendingIntent= PendingIntent.getActivity(this, notifyId, intent, flags);
        return pendingIntent;
    }
    /**
     * 显示带进度条通知栏
     * @param indeterminate 是否确定进度
     * @param progress 当前进度，进度条最大是100，如果进度为不确定的即indeterminate值为true，则此参数无效
     */
    public void showProgressNotify(boolean indeterminate,int progress) {
        mBuilder.setContentTitle("等待下载")
                .setContentText("进度:")
                .setTicker("开始下载");// 通知首次出现在通知栏，带上升动画效果的
        if(indeterminate){
            //不确定进度的
            mBuilder.setProgress(0, 0, true);
        }else{
            //确定进度的
            mBuilder.setProgress(100, progress, false); // 这个方法是显示进度条  设置为true就是不确定的那种进度条效果
        }
        mNotificationManager.notify(notifyId, mBuilder.build());
    }
    /** 设置下载进度 */
    public void setNotify(int progress) {
        mBuilder.setContentTitle("下载中...")
                .setContentText("进度:"+progress+"%")
                .setTicker("开始下载");// 通知首次出现在通知栏，带上升动画效果的
        mBuilder.setProgress(100, progress, false); // 这个方法是显示进度条
        mNotificationManager.notify(notifyId, mBuilder.build());
    }

    /**
     * 下载结束通知点击安装
     */
    public void setNotifyInstall(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(Config.BASE_DIR+ "/myhome.apk")),
                "application/vnd.android.package-archive");
        mBuilder.setContentIntent(getDefaultIntent(Notification.FLAG_AUTO_CANCEL,intent));
        mBuilder.setContentTitle("下载完成");
        mBuilder.setContentText("点击安装更新");
        mNotificationManager.notify(notifyId, mBuilder.build());
    }
    /**
     * 清除当前创建的通知栏
     */
    public void clearNotify(int notifyId){
        mNotificationManager.cancel(notifyId);//删除一个特定的通知ID对应的通知
    }

    /**
     * 清除所有通知栏
     * */
    public void clearAllNotify() {
        mNotificationManager.cancelAll();// 删除你发的所有通知
    }

    /**
     * 安装apk文件
     * @param apkPath
     * @param apkName
     */
    public void installApk(String apkPath, String apkName) {
        File apkFile = new File(apkPath, apkName);
        if (!apkFile.exists()) {
            return;
        }
        // 通过Intent安装APK文件
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkFile.toString()),
                "application/vnd.android.package-archive");
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
    public class AppReceiver extends BroadcastReceiver {
        private final String TAG = this.getClass().getSimpleName();
        @Override
        public void onReceive(Context context, Intent intent) {
            PackageManager pm = context.getPackageManager();
            if (TextUtils.equals(intent.getAction(), Intent.ACTION_PACKAGE_ADDED)) {
                String packageName = intent.getData().getSchemeSpecificPart();
                if(packageName.equals("goujiawang.myhome")){
                    PrintUtils.d(TAG, "--------安装成功" + packageName);
                    PrintUtils.showToast("安装成功" + packageName);
                    clearNotify(notifyId);
                }


            } else
            if (TextUtils.equals(intent.getAction(), Intent.ACTION_PACKAGE_REPLACED)) {
                String packageName = intent.getData().getSchemeSpecificPart();
                if(packageName.equals("goujiawang.myhome")){
                    PrintUtils.d(TAG, "--------替换成功" + packageName);
                    PrintUtils.showToast("替换成功" + packageName);
                    clearNotify(notifyId);
                }

            }
//            else if (TextUtils.equals(intent.getAction(), Intent.ACTION_PACKAGE_REMOVED)) {
//                String packageName = intent.getData().getSchemeSpecificPart();
//                PrintUtils.d(TAG, "--------卸载成功" + packageName);
//                PrintUtils.showToast("卸载成功" + packageName);
//            }
        }

    }
}
