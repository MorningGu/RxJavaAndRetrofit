package com.example.gulei.rxjavaandretrofit.common.utils;


import android.content.Context;
import android.content.res.Resources;

import com.example.gulei.rxjavaandretrofit.GApplication;

/**
 * 像素方法类
 * @class PixelUtil.java
 * @author hewenlong
 * @time 2014年10月21日
 * @desc
 */
public class PixelUtil {


    /**
     * dpת px.
     *
     * @param value the value
     * @return the int
     */
    public static int dp2px(float value) {
        final float scale = GApplication.getInstance().getResources().getDisplayMetrics().densityDpi;
        return (int) (value * (scale / 160) + 0.5f);
    }

    /**
     * dpת px.
     *
     * @param value   the value
     * @param context the context
     * @return the int
     */
    public static int dp2px(float value, Context context) {
        final float scale = context.getResources().getDisplayMetrics().densityDpi;
        return (int) (value * (scale / 160) + 0.5f);
    }

    /**
     * pxתdp.
     *
     * @param value the value
     * @return the int
     */
    public static int px2dp(float value) {
        final float scale = GApplication.getInstance().getResources().getDisplayMetrics().densityDpi;
        return (int) ((value * 160) / scale + 0.5f);
    }

    /**
     * pxתdp.
     *
     * @param value   the value
     * @param context the context
     * @return the int
     */
    public static int px2dp(float value, Context context) {
        final float scale = context.getResources().getDisplayMetrics().densityDpi;
        return (int) ((value * 160) / scale + 0.5f);
    }

    /**
     * spתpx.
     *
     * @param value the value
     * @return the int
     */
    public static int sp2px(float value) {
        Resources r;
        if (GApplication.getInstance() == null) {
            r = Resources.getSystem();
        } else {
            r = GApplication.getInstance().getResources();
        }
        float spvalue = value * r.getDisplayMetrics().scaledDensity;
        return (int) (spvalue + 0.5f);
    }

    /**
     * spתpx.
     *
     * @param value   the value
     * @param context the context
     * @return the int
     */
    public static int sp2px(float value, Context context) {
        Resources r;
        if (context == null) {
            r = Resources.getSystem();
        } else {
            r = context.getResources();
        }
        float spvalue = value * r.getDisplayMetrics().scaledDensity;
        return (int) (spvalue + 0.5f);
    }

    /**
     * pxתsp.
     *
     * @param value the value
     * @return the int
     */
    public static int px2sp(float value) {
        final float scale = GApplication.getInstance().getResources().getDisplayMetrics().scaledDensity;
        return (int) (value / scale + 0.5f);
    }

    /**
     * pxתsp.
     *
     * @param value   the value
     * @param context the context
     * @return the int
     */
    public static int px2sp(float value, Context context) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (value / scale + 0.5f);
    }

}
