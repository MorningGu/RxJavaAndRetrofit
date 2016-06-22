package com.example.gulei.rxjavaandretrofit.ui.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.gulei.rxjavaandretrofit.R;


/**
 * Created by hank on 2015/7/23.
 */
public class LoadingDialog extends Dialog {

   private Context context;
   private boolean isTouchDismiss = true;

   public LoadingDialog(Context context, boolean isTouchDismiss) {
      //设置style
      super(context, R.style.alert);
      this.context = context;
      this.isTouchDismiss = isTouchDismiss;
   }

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.layout_loading);
      setCanceledOnTouchOutside(isTouchDismiss);
      setScreenBgLight();
   }

   // 设置屏幕背景透明
   private void setScreenBgLight() {
      WindowManager.LayoutParams lp = getWindow().getAttributes();
      lp.alpha = 1.0f;//透明度
      //lp.dimAmount = 0.3f;//暗度0-1
      lp.dimAmount = 0.1f;//暗度0-1
      getWindow().setAttributes(lp);
   }

   public void setOnTouchOutside(boolean isShow) {
      this.isTouchDismiss = isTouchDismiss;
   }
}
