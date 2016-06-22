package com.example.gulei.rxjavaandretrofit.common.entity.user;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建人：DD
 * 类描述：登录返回数据
 * 创建时间：2015/10/14  16:21
 */
public class UserData implements Serializable {

   private UserInfo userInfo;
   private Artist artist;
   private Teacher teacher;
   private HelpChildren helpChildren;
   private String type;
   private String loginMobile;
   private String isActivate;

   private List<Region> region=new ArrayList<Region>();

   private String getReginStr(String indexStr) {
      String r="";
      for (Region reg : getRegion()) {
         if (indexStr.equals(reg.getLevel())) {
            r= reg.getName();
         }
      }
      return TextUtils.isEmpty(r)?"":r;
   }

   public void setRegion(List<Region> region) {
      this.region = region;
   }

   public List<Region> getRegion() {
      return region;
   }

   public String getProvinceName() {

      return getReginStr("1");
   }

   public String getDistrictCode() {
      String r="";
      for (Region reg : getRegion()) {
         if ("3".equals(reg.getLevel())) {
            r= reg.getRegionCode();
         }
      }
      return r;
   }

   public String getCityName() {
      return getReginStr("2");
   }

   public String getDistrictName() {
      return getReginStr("3");
   }

   public UserInfo getUserInfo() {
      return userInfo;
   }

   public void setUserInfo(UserInfo userInfo) {
      this.userInfo = userInfo;
   }

   public Artist getArtist() {
      return artist;
   }

   public void setArtist(Artist artist) {
      this.artist = artist;
   }

   public Teacher getTeacher() {
      return teacher;
   }

   public void setTeacher(Teacher teacher) {
      this.teacher = teacher;
   }

   public HelpChildren getHelpChildren() {
      return helpChildren;
   }

   public void setHelpChildren(HelpChildren helpChildren) {
      this.helpChildren = helpChildren;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getLoginMobile() {
      return loginMobile;
   }

   public void setLoginMobile(String loginMobile) {
      this.loginMobile = loginMobile;
   }

   public String getIsActivate() {
      return isActivate;
   }

   public void setIsActivate(String isActivate) {
      this.isActivate = isActivate;
   }
}
