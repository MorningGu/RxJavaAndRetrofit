
package com.example.gulei.rxjavaandretrofit.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import com.example.gulei.rxjavaandretrofit.Config;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;


/**
 * ImageLoaderUtils
 */
public enum ImageLoaderUtils {
    INSTANCE;
    private static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();//分配的可用内存
    public static final int MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 4;//使用的缓存数量
    private Context mContext;

    /**
     * 初始化
     * @param context
     */
    public void init(Context context,Bitmap.Config config){
        this.mContext = context;
        Fresco.initialize(context,initImagePipelineConfig(config));
    }

    /**
     * 获取配置
     * @param config
     * @return
     */
    private ImagePipelineConfig initImagePipelineConfig(Bitmap.Config config){
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(mContext)
                .setBaseDirectoryPath(new File(Config.BASE_DIR,"Cache"))
                .setBaseDirectoryName(Config.CACHE_DIR)
                .setMaxCacheSize(Config.CACHE_SIZE_DISK)//磁盘缓存
                .build();
        final MemoryCacheParams bitmapCacheParams = new MemoryCacheParams(
                MAX_MEMORY_CACHE_SIZE, // 内存缓存中总图片的最大大小,以字节为单位。
                Integer.MAX_VALUE,      // 内存缓存中图片的最大数量。
                MAX_MEMORY_CACHE_SIZE,// 内存缓存中准备清除但尚未被删除的总图片的最大大小,以字节为单位。
                Integer.MAX_VALUE,     // 内存缓存中准备清除的总图片的最大数量。
                Integer.MAX_VALUE);    // 内存缓存中单个图片的最大大小。

        Supplier<MemoryCacheParams> mSupplierMemoryCacheParams = new Supplier<MemoryCacheParams>() {
            @Override
            public MemoryCacheParams get() {
                return bitmapCacheParams;
            }
        };
        return  ImagePipelineConfig.newBuilder(mContext)
                .setBitmapMemoryCacheParamsSupplier(mSupplierMemoryCacheParams)//内存缓存配置（一级缓存，已解码的图片）
                .setMainDiskCacheConfig(diskCacheConfig)
                .setBitmapsConfig(config)
                .setDownsampleEnabled(true)//以打开对png等图片的自动缩放特性(缩放必须要设置ResizeOptions)
                .setResizeAndRotateEnabledForNetwork(true)
                .build();
    }
//    /**
//     * 展示图片
//     * @param imageView
//     * @param defaultDrawable
//     * @param width
//     * @param height
//     */
//    public void displayImage(String uri, final FImageView imageView, final Drawable defaultDrawable, int width, int height) {
//        Resources resources = context.getResources();
//        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(resources)
//                .setFadeDuration(300)
////                .setProgressBarImage(new ProgressBarDrawable())
//                .build();
//        if(defaultDrawable!=null){
//            hierarchy.setPlaceholderImage(defaultDrawable);
//            hierarchy.setFailureImage(defaultDrawable);
//        }
//        final DraweeHolder<GenericDraweeHierarchy> draweeHolder = DraweeHolder.create(hierarchy, context);
//        imageView.setOnImageViewListener(new FImageView.OnImageViewListener() {
//            @Override
//            public void onDetach() {
//                draweeHolder.onDetach();
//            }
//
//            @Override
//            public void onAttach() {
//                draweeHolder.onAttach();
//            }
//
//            @Override
//            public boolean verifyDrawable(Drawable dr) {
//                if (dr == draweeHolder.getHierarchy().getTopLevelDrawable()) {
//                    return true;
//                }
//                return false;
//            }
//
//            @Override
//            public void onDraw(Canvas canvas) {
//                Drawable drawable = draweeHolder.getHierarchy().getTopLevelDrawable();
//                if (drawable == null) {
//                    if(defaultDrawable!=null){
//                        imageView.setImageDrawable(defaultDrawable);
//                    }
//                } else {
//                    imageView.setImageDrawable(drawable);
//                }
//            }
//        });
//
//        ImageRequest imageRequest = ImageRequestBuilder
//                .newBuilderWithSource(Uri.parse(uri))
//                .setResizeOptions(new ResizeOptions(width, height))//图片目标大小
//                .build();
//        DraweeController controller = Fresco.newDraweeControllerBuilder()
//                .setOldController(draweeHolder.getController())
//                .setImageRequest(imageRequest)
//                .build();
//        draweeHolder.setController(controller);
//    }

    /**
     * 为SimpleDraweeView 显示图片
     * @param uri
     * @param draweeView
     * @param width
     * @param height
     */
    public void displayImage(String uri, SimpleDraweeView draweeView,int width, int height){
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(uri))
                .setResizeOptions(new ResizeOptions(width, height))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(draweeView.getController())
                .setControllerListener(new BaseControllerListener<ImageInfo>())
                .build();
        draweeView.setController(controller);
    }
}
