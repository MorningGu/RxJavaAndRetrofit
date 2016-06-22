package com.example.gulei.rxjavaandretrofit.common.entity.user;
/**
 * 创建人：Administrator
 * 类描述：
 * 创建时间：2016/1/12  14:11
 */
public class ArtistBase {

   private int id;
   private int status;
   private Artist artistInfo;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getStatus() {
      return status;
   }

   public void setStatus(int status) {
      this.status = status;
   }

   public Artist getArtistInfo() {
      return artistInfo;
   }

   public void setArtistInfo(Artist artistInfo) {
      this.artistInfo = artistInfo;
   }
}
