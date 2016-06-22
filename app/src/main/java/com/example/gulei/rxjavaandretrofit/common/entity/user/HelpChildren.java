package com.example.gulei.rxjavaandretrofit.common.entity.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建人：DD
 * 类描述：受帮助的小朋友
 * 创建时间：2015/10/14  16:24
 */
public class HelpChildren extends UserBase implements Serializable {
   private String img;// 照片
   private String thumbnailImg;// 照片(缩略图)
   private String name; // 姓名
   private String photograph;// 照片
   private String male;// 性别
   private String birthday;// 生日
   private String zodiac;// 属相
   private String constellation;// 星座
   private String nation;// 民族
   private String origin;// 籍贯
   private String address;// 通讯地址
   private String idCode;// 身份证号
   private String parentMobile;// 家长手机号
   private String parnetIdCode;// 家长身份证号
   private String parentName;// 家长姓名
   private String currentStatus;// 目前状态
   private String wish;// 最大的愿望
   private String significative;// 最有意愿的事
   private String age;//年龄
   private double dreamFund;//梦想基金

   private String alipayAccount;//支付宝账户
   private String weChatAccount;//微信账户
   private String bankAccount;//银行卡号
   private String accountName;

   private List<WithPicture> withPictureList = new ArrayList<WithPicture>();

   private UserBase user;

   public UserBase getUser() {
      return user;
   }

   public void setUser(UserBase user) {
      this.user = user;
   }

   public String getSex() {
      if ("true".equals(male)) {
         return "男";
      } else if ("false".equals(male)) {
         return "女";
      } else {
         return "";
      }
   }

   public List<WithPicture> getWithPictureList() {
      return withPictureList;
   }

   public void setWithPictureList(List<WithPicture> withPictureList) {
      this.withPictureList = withPictureList;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPhotograph() {
      return photograph;
   }

   public void setPhotograph(String photograph) {
      this.photograph = photograph;
   }

   public String getMale() {
      return male;
   }

   public void setMale(String male) {
      this.male = male;
   }

   public String getBirthday() {
      return birthday;
   }

   public void setBirthday(String birthday) {
      this.birthday = birthday;
   }

   public String getZodiac() {
      return zodiac;
   }

   public void setZodiac(String zodiac) {
      this.zodiac = zodiac;
   }

   public String getConstellation() {
      return constellation;
   }

   public void setConstellation(String constellation) {
      this.constellation = constellation;
   }

   public String getNation() {
      return nation;
   }

   public void setNation(String nation) {
      this.nation = nation;
   }

   public String getOrigin() {
      return origin;
   }

   public void setOrigin(String origin) {
      this.origin = origin;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getIdCode() {
      return idCode;
   }

   public void setIdCode(String idCode) {
      this.idCode = idCode;
   }

   public String getParentMobile() {
      return parentMobile;
   }

   public void setParentMobile(String parentMobile) {
      this.parentMobile = parentMobile;
   }

   public String getParnetIdCode() {
      return parnetIdCode;
   }

   public void setParnetIdCode(String parnetIdCode) {
      this.parnetIdCode = parnetIdCode;
   }

   public String getParentName() {
      return parentName;
   }

   public void setParentName(String parentName) {
      this.parentName = parentName;
   }

   public String getCurrentStatus() {
      return currentStatus;
   }

   public void setCurrentStatus(String currentStatus) {
      this.currentStatus = currentStatus;
   }

   public String getWish() {
      return wish;
   }

   public void setWish(String wish) {
      this.wish = wish;
   }

   public String getSignificative() {
      return significative;
   }

   public void setSignificative(String significative) {
      this.significative = significative;
   }

   public String getAge() {
      return age;
   }

   public void setAge(String age) {
      this.age = age;
   }

   public double getDreamFund() {
      return dreamFund;
   }

   public void setDreamFund(double dreamFund) {
      this.dreamFund = dreamFund;
   }

   public String getImg() {
      return img;
   }

   public void setImg(String img) {
      this.img = img;
   }

   public String getThumbnailImg() {
      return thumbnailImg;
   }

   public void setThumbnailImg(String thumbnailImg) {
      this.thumbnailImg = thumbnailImg;
   }

   public String getAlipayAccount() {
      return alipayAccount;
   }

   public void setAlipayAccount(String alipayAccount) {
      this.alipayAccount = alipayAccount;
   }

   public String getBankAccount() {
      return bankAccount;
   }

   public void setBankAccount(String bankAccount) {
      this.bankAccount = bankAccount;
   }

   public String getWeChatAccount() {
      return weChatAccount;
   }

   public void setWeChatAccount(String weChatAccount) {
      this.weChatAccount = weChatAccount;
   }

   public String getAccountName() {
      return accountName;
   }

   public void setAccountName(String accountName) {
      this.accountName = accountName;
   }
}
