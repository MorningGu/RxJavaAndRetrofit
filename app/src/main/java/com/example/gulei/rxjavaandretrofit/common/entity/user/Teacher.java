package com.example.gulei.rxjavaandretrofit.common.entity.user;
import java.io.Serializable;

/**
 * 创建人：DD
 * 类描述：老师
 * 创建时间：2015/10/14  16:24
 */
public class Teacher extends UserBase implements Serializable {
   private String img;// 照片
   private String thumbnailImg;// 照片(缩略图)
   private String name;// 姓名
   private String male;// 性别
   private String birthday;// 生日
   private String workUnit;// 工作单位
   private String mobile;// 手机号
   private String celtyl;// 教师职工证
   private String thumbnailCeltyl;
   private String region;// 城市
   private String nation;//民族
   private String age;
   private String constellation;
   private String zodiac;

   public String getSex() {
      if ("true".equals(male)) {
         return "男";
      } else if("false".equals(male)){
         return "女";
      }else{
         return "";
      }
   }

   public String getAge() {
      return age;
   }

   public void setAge(String age) {
      this.age = age;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
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

   public String getWorkUnit() {
      return workUnit;
   }

   public void setWorkUnit(String workUnit) {
      this.workUnit = workUnit;
   }

   public String getMobile() {
      return mobile;
   }

   public void setMobile(String mobile) {
      this.mobile = mobile;
   }

   public String getCeltyl() {
      return celtyl;
   }

   public void setCeltyl(String celtyl) {
      this.celtyl = celtyl;
   }

   public String getRegion() {
      return region;
   }

   public void setRegion(String region) {
      this.region = region;
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

   public String getThumbnailImg() {
      return thumbnailImg;
   }

   public void setThumbnailImg(String thumbnailImg) {
      this.thumbnailImg = thumbnailImg;
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

   public String getThumbnailCeltyl() {
      return thumbnailCeltyl;
   }

   public void setThumbnailCeltyl(String thumbnailCeltyl) {
      this.thumbnailCeltyl = thumbnailCeltyl;
   }
}
