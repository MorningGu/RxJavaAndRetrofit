# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Develop\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-optimizationpasses 5                           #->设置混淆的压缩比率 0 ~ 7
-dontusemixedcaseclassnames                    # -> Aa aA
-dontskipnonpubliclibraryclasses                #->如果应用程序引入的有jar包,并且想混淆jar包里面的class
-dontpreverify
-verbose                                        #->混淆后生产映射文件 map 类名->转化后类名的映射
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*       #->混淆采用的算法.

-keep public class * extends android.app.Activity                        # ->所有activity的子类不要去混淆
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keep public class [com.example.gulei.rxjavaandretrofit].R$*{
    public static final int *;
}
#-dontwarn android.support.v4.**
-dontwarn android.annotation


#-libraryjars libs/android-support-v4.jar


-keepclasseswithmembernames class * {           ## -> 所有native的方法不能去混淆.
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);           ## -->某些构造方法不能去混淆
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}
-keepclassmembers enum * {                                ##-> 枚举类不能去混淆
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {             ## -> aidl文件不能去混淆.
  public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}

#-keep class android.support.v4.**{*;}
-keepattributes *Annotation*


##第三方包的混淆配置####

#facebook fresco start -------------------------------------------------
# Keep our interfaces so they can be used by other ProGuard rules.
# See http://sourceforge.net/p/proguard/bugs/466/
-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip
# Do not strip any method/class that is annotated with @DoNotStrip
-keep @com.facebook.common.internal.DoNotStrip class *
-keepclassmembers class * {@com.facebook.common.internal.DoNotStrip *;}
# can not display gif image.
-keep class com.facebook.imagepipeline.animated.factory.AnimatedFactoryImpl {
    public AnimatedFactoryImpl(com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory, com.facebook.imagepipeline.core.ExecutorSupplier);
}
-keep class com.facebook.animated.gif.** {*;}
-dontwarn javax.annotation.**
# facebook fresco end -------------------------------------------------

#okhttp
-keep class com.squareup.okhttp.**{*;}
-keep interface com.squareup.okhttp.**{*;}
-dontwarn com.squareup.okhttp.**

#okhttp3
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}
-keep interface okhttp3.**{*;}

#okio
-dontwarn okio.**
-keep class okio.**{*;}
-keep interface okio.**{*;}

#rxjava
-keep class rx.**{*;}
-keep interface rx.** {*;}
-dontwarn rx.**

#gson
-keep class com.google.gson.**{*;}
-dontwarn com.google.gson.**

#retrofit2
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keep interface retrofit2.** {*;}
-keepattributes Signature
-keepattributes Exceptions
