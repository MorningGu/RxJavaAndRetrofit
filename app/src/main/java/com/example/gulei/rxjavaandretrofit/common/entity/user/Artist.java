package com.example.gulei.rxjavaandretrofit.common.entity.user;

import java.io.Serializable;

/**
 * 创建人：DD
 * 类描述：画家
 * 创建时间：2015/10/14  16:30
 */
public class Artist extends UserBase implements Serializable {

   //作者
   private String img;//参赛者照片
   private String name;//参赛者姓名
   private String male;//性别
   private String nation;//名族
   private String zodiac;//生肖
   private String constellation;//星座
   private String birthday;//生日
   private String age;//年龄
   private String idCode;//身份证
   private String origin;//籍贯
   private String school;//学校
   private String parentMobile;//家长手机号
   private String cartoon;//最喜欢的卡通形象
   private String orginimg;//参赛者照片原图

   private String trainingInstitution;//培训机构

   //推荐方
   private String referrerSchool;//推荐方学校/培训机构
   private String referrerMobile;//推荐方联系方式
   private String teacher;//组织老师
   private String teacherMobile;//组织老师联系方式
   private String instructor;//指导老师
   private String instructorMobile;//指导老师联系方式

   private String alipayAccount;//支付宝账户
   private String bankAccount;//银行卡号
   private String weChatAccount;//微信账户
   private String accountName;

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
      } else {
         return "女";
      }
   }

   public String getImg() {
      return img;
   }

   public void setImg(String img) {
      this.img = img;
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

   public String getNation() {
      return nation;
   }

   public void setNation(String nation) {
      this.nation = nation;
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

   public String getBirthday() {
      return birthday;
   }

   public void setBirthday(String birthday) {
      this.birthday = birthday;
   }

   public String getAge() {
      return age;
   }

   public void setAge(String age) {
      this.age = age;
   }

   public String getOrigin() {
      return origin;
   }

   public void setOrigin(String origin) {
      this.origin = origin;
   }

   public String getSchool() {
      return school;
   }

   public void setSchool(String school) {
      this.school = school;
   }


   public String getTeacher() {
      return teacher;
   }

   public void setTeacher(String teacher) {
      this.teacher = teacher;
   }


   public String getInstructor() {
      return instructor;
   }

   public void setInstructor(String instructor) {
      this.instructor = instructor;
   }


   public String getCartoon() {
      return cartoon;
   }

   public void setCartoon(String cartoon) {
      this.cartoon = cartoon;
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

   public String getReferrerSchool() {
      return referrerSchool;
   }

   public void setReferrerSchool(String referrerSchool) {
      this.referrerSchool = referrerSchool;
   }

   public String getReferrerMobile() {
      return referrerMobile;
   }

   public void setReferrerMobile(String referrerMobile) {
      this.referrerMobile = referrerMobile;
   }

   public String getTeacherMobile() {
      return teacherMobile;
   }

   public void setTeacherMobile(String teacherMobile) {
      this.teacherMobile = teacherMobile;
   }

   public String getInstructorMobile() {
      return instructorMobile;
   }

   public void setInstructorMobile(String instructorMobile) {
      this.instructorMobile = instructorMobile;
   }

   public String getOrginimg() {
      return orginimg;
   }

   public void setOrginimg(String orginimg) {
      this.orginimg = orginimg;
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

   public String getTrainingInstitution() {
      return trainingInstitution;
   }

   public void setTrainingInstitution(String trainingInstitution) {
      this.trainingInstitution = trainingInstitution;
   }
}
