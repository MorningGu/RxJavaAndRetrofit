package com.example.gulei.rxjavaandretrofit.common.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;

import com.example.gulei.rxjavaandretrofit.R;

/**
 * Created by gulei on 2016/5/3 0003.
 */
public class DialogUtils {
    /**
     * 显示安卓原生的dialog
     * @param context
     * @param title
     * @param msg
     * @param cancelable
     * @param onOkListener
     */
    public static void showDialog(Context context, String title, String msg, boolean cancelable, DialogInterface.OnClickListener onOkListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.MyAlertDialog));
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setCancelable(cancelable);
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", onOkListener);
        builder.show();
    }
}
