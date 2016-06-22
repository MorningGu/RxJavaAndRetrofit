package com.example.gulei.rxjavaandretrofit.common.entity.user;
/**
 * 创建人：DD
 * 类描述：帮助小朋友  生活照
 * 创建时间：2015/10/23  11:14
 */
public class WithPicture {

   /**
    * id : 22
    * invalid : false
    * status : 1
    * createdDatetime : 1445514347000
    * updatedDatetime : null
    * orderTag : 1
    * path : http://timage.goujiawang.com/test_store/withPicture/thumb_1445514315704831953.jpg
    * originPath : http://timage.goujiawang.com/test_store/withPicture/1445514315704831953.jpg
    * description : null
    * type : 3
    * token : null
    */
   private String id;
   private String invalid;
   private String status;
   private String createdDatetime;
   private String updatedDatetime;
   private String orderTag;
   private String path;
   private String originPath;
   private String description;
   private String type;
   private String token;

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getInvalid() {
      return invalid;
   }

   public void setInvalid(String invalid) {
      this.invalid = invalid;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getCreatedDatetime() {
      return createdDatetime;
   }

   public void setCreatedDatetime(String createdDatetime) {
      this.createdDatetime = createdDatetime;
   }

   public String getUpdatedDatetime() {
      return updatedDatetime;
   }

   public void setUpdatedDatetime(String updatedDatetime) {
      this.updatedDatetime = updatedDatetime;
   }

   public String getOrderTag() {
      return orderTag;
   }

   public void setOrderTag(String orderTag) {
      this.orderTag = orderTag;
   }

   public String getPath() {
      return path;
   }

   public void setPath(String path) {
      this.path = path;
   }

   public String getOriginPath() {
      return originPath;
   }

   public void setOriginPath(String originPath) {
      this.originPath = originPath;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getToken() {
      return token;
   }

   public void setToken(String token) {
      this.token = token;
   }
}
