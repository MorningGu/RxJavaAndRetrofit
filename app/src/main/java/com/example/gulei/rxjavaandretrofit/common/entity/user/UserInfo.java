package com.example.gulei.rxjavaandretrofit.common.entity.user;
import java.io.Serializable;

/**
 * 创建人：DD
 * 类描述：普通用户
 * 创建时间：2015/10/14  16:23
 */
public class UserInfo extends UserBase implements Serializable{

   private String male;

   private String birthday;

   private String region;//城市

   private String name;  //用户登录名/昵称

   private String constellation;//所属星座，例如：狮子座

   private String zodiac;// 生肖

   private String idCode;// 身份证号码

   private String nation;// 民族

   private String img;//普通用户头像   缩略图

   private String originImg;//普通用户头像   原图

   private String type; //用户类型

   private String profession;//职业

   private String mobile;//联系方式

   private String nickName;//用户真实姓名

   private String account;//账户名

   private String balance;//账户余额

   private String invitationCode;//邀请码字段

   public String getSex() {
      if ("true".equals(male)) {
         return "男";
      } else if("false".equals(male)){
         return "女";
      }else{
         return "";
      }
   }

   public void setMale(String male) {
      this.male = male;
   }

   public String getMale() {
      return male;
   }

   public String getBirthday() {
      return birthday;
   }

   public void setBirthday(String birthday) {
      this.birthday = birthday;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getConstellation() {
      return constellation;
   }

   public void setConstellation(String constellation) {
      this.constellation = constellation;
   }

   public String getZodiac() {
      return zodiac;
   }

   public void setZodiac(String zodiac) {
      this.zodiac = zodiac;
   }

   public String getIdCode() {
      return idCode;
   }

   public void setIdCode(String idCode) {
      this.idCode = idCode;
   }

   public String getNation() {
      return nation;
   }

   public void setNation(String nation) {
      this.nation = nation;
   }

   public String getImg() {
      return img;
   }

   public void setImg(String img) {
      this.img = img;
   }

   public String getOriginImg() {
      return originImg;
   }

   public void setOriginImg(String originImg) {
      this.originImg = originImg;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getProfession() {
      return profession;
   }

   public void setProfession(String profession) {
      this.profession = profession;
   }

   public String getMobile() {
      return mobile;
   }

   public void setMobile(String mobile) {
      this.mobile = mobile;
   }

   public String getNickName() {
      return nickName;
   }

   public void setNickName(String nickName) {
      this.nickName = nickName;
   }

   public String getAccount() {
      return account;
   }

   public void setAccount(String account) {
      this.account = account;
   }

   public String getBalance() {
      return balance;
   }

   public void setBalance(String balance) {
      this.balance = balance;
   }

   public String getRegion() {
      return region;
   }

   public void setRegion(String region) {
      this.region = region;
   }

   public String getInvitationCode() {
      return invitationCode;
   }

   public void setInvitationCode(String invitationCode) {
      this.invitationCode = invitationCode;
   }
}
