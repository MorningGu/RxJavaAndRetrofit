package com.example.gulei.rxjavaandretrofit.common.entity;

/**
 * 服务器返回的最外层的json格式
 * Created by gulei on 2016/6/17 0017.
 */
public class JsonResult<T> {
    private String msg;
    private int returnCode;
    private T data;
    private boolean success;
    private String result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
