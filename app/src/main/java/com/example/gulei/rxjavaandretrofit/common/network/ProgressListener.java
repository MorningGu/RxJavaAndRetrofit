package com.example.gulei.rxjavaandretrofit.common.network;

/**
 * 日期：16/3/17 20:37
 * <p>
 * 描述：下载进度监听
 * 修复：
 */
public interface ProgressListener
{
    void update(long bytesRead, long contentLength, boolean done);
}
