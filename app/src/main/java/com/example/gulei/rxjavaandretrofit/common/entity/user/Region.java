package com.example.gulei.rxjavaandretrofit.common.entity.user;
import java.io.Serializable;

/**
 * 创建人：Administrator
 * 类描述：
 * 创建时间：2015/10/21  19:47
 */
public class Region implements Serializable{

   private String regionCode;
   private String level;
   private String name;

   public String getLevel() {
      return level;
   }

   public void setLevel(String level) {
      this.level = level;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getRegionCode() {

      return regionCode;
   }

   public void setRegionCode(String regionCode) {
      this.regionCode = regionCode;
   }
}
