package cn.gulei.library.scan;

import android.graphics.Bitmap;

/**
 * Created by gulei on 2016/8/10 0010.
 */
public class OpenCVHelper {
    static {
        System.loadLibrary("Scanner");
    }
    public native Bitmap getScannedBitmap(Bitmap bitmap, float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4);

    public native float[] getPoints(Bitmap bitmap);
}
