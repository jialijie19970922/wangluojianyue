package com.example.administrator.sixgroupsofprojects.modle.bean;

/**
 * Created by Administrator on 2018/6/11/011.
 */

public class BaseBean<T> {
    private String erron;
    private String msg;
    private T data;

    public String getErron() {
        return erron;
    }

    public void setErron(String erron) {
        this.erron = erron;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}