package cn.gulei.library.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 图片处理的工具类
 * Created by gulei on 2016/5/23 0023.
 */
public class BitmapUtils {

    /**
     * 从文件中加载bitmap
     * @param imagePath
     * @param requestWidth  Config.IMAGE_SMALL  Config.IMAGE_NORMAL Config.IMAGE_BIG
     * @param requestHeight
     * @return
     */
    public static Bitmap decodeBitmapFromFile(String imagePath, int requestWidth, int requestHeight) {
        String TAG = "BitmapUtils.decodeBitmapFromFile -->";
        if (!TextUtils.isEmpty(imagePath)) {
            LogUtils.i(TAG, "requestWidth: " + requestWidth);
            LogUtils.i(TAG, "requestHeight: " + requestHeight);
            if (requestWidth <= 0 || requestHeight <= 0) {
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                return bitmap;
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;//不加载图片到内存，仅获得图片宽高
            BitmapFactory.decodeFile(imagePath, options);
            LogUtils.i(TAG, "original height: " + options.outHeight);
            LogUtils.i(TAG, "original width: " + options.outWidth);
            if (options.outHeight == -1 || options.outWidth == -1) {
                try {
                    ExifInterface exifInterface = new ExifInterface(imagePath);
                    int height = exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_LENGTH, ExifInterface.ORIENTATION_NORMAL);//获取图片的高度
                    int width = exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH, ExifInterface.ORIENTATION_NORMAL);//获取图片的宽度
                    LogUtils.i(TAG, "exif height: " + height);
                    LogUtils.i(TAG, "exif width: " + width);
                    options.outWidth = width;
                    options.outHeight = height;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            options.inSampleSize = calculateInSampleSize(options, requestWidth, requestHeight); //计算获取新的采样率
            LogUtils.i(TAG, "inSampleSize: " + options.inSampleSize);
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeFile(imagePath, options);

        } else {
            return null;
        }
    }

    /**
     * 计算缩放比例
     * @param options
     * @param reqWidth  Config.IMAGE_SMALL  Config.IMAGE_NORMAL Config.IMAGE_BIG
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        String TAG = "BitmapUtils.calculateInSampleSize -->";
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        LogUtils.i(TAG, "height: " + height);
        LogUtils.i(TAG, "width: " + width);
        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }

            long totalPixels = width * height / inSampleSize;

            final long totalReqPixelsCap = reqWidth * reqHeight * 2;

            while (totalPixels > totalReqPixelsCap) {
                inSampleSize *= 2;
                totalPixels /= 2;
            }
        }
        return inSampleSize;
    }
    public static Bitmap compressImageZl(Bitmap image) {
        if(image==null){
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        LogUtils.d("compressImageZl","-------"+baos.toByteArray().length);
        while (baos.toByteArray().length / 1024 > 1024) { //循环判断如果压缩后图片是否大于1M,大于继续压缩
            LogUtils.d("compressImageZl","-------"+baos.toByteArray().length);
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }
}
