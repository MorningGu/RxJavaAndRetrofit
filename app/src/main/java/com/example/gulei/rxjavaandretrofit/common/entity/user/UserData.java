package com.example.gulei.rxjavaandretrofit.common.entity.user;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建人：DD
 * 类描述：登录返回数据
 * 创建时间：2015/10/14  16:21
 */
public class UserData implements Serializable {
   @SerializedName("userInfo")
   private UserInfo userInfo;

   public UserInfo getUserInfo() {
      return userInfo;
   }

   public void setUserInfo(UserInfo userInfo) {
      this.userInfo = userInfo;
   }


}
