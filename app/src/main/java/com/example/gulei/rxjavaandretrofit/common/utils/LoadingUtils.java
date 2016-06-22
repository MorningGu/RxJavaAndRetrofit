package com.example.gulei.rxjavaandretrofit.common.utils;

import android.content.Context;

import com.example.gulei.rxjavaandretrofit.ui.view.dialog.LoadingDialog;


/**
 * Created by hank on 2015/7/22.
 */
public class LoadingUtils {
   
   private LoadingDialog loadingDialog;
   private Context context;
   private boolean isTouchDismiss = true;

   public LoadingUtils(Context context, boolean isShow) {
      this.context = context;
      this.isTouchDismiss = isShow;
   }


   public void showLoading() {
      if (loadingDialog == null) {
         loadingDialog = new LoadingDialog(context, isTouchDismiss);
      }
      if (!loadingDialog.isShowing()) {
         loadingDialog.show();
      }
   }

   public void dismissLoading() {
      if (loadingDialog != null && loadingDialog.isShowing()) {
         loadingDialog.dismiss();
      }
   }
}
