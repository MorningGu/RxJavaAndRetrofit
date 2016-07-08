package com.example.gulei.rxjavaandretrofit.common.entity;

/**
 * Created by gulei on 2016/7/8 0008.
 */
public class Version {
    private VersionInfo version;

    public VersionInfo getVersion() {
        return version;
    }

    public void setVersion(VersionInfo version) {
        this.version = version;
    }

   public class VersionInfo{
        private String versions;
        private String serviceAddress;
        private int size;

        public String getVersions() {
            return versions;
        }

        public void setVersions(String versions) {
            this.versions = versions;
        }

        public String getServiceAddress() {
            return serviceAddress;
        }

        public void setServiceAddress(String serviceAddress) {
            this.serviceAddress = serviceAddress;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }

}
