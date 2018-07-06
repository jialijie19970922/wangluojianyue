package com.example.administrator.sixgroupsofprojects.modle.interceptor;

import android.util.Log;

import com.example.administrator.sixgroupsofprojects.modle.bean.BaseBean;

import rx.Subscriber;


/**
 * 拦截器
 * Created by Administrator on 2018/6/11/011.
 */

public abstract class BaseBeanSubscribe<T extends BaseBean> extends Subscriber<T> {
    private Log LogUtils;

    public enum ERROR_TYPE {
        DATA_EMPTY,//数据为空
        DATA_ERROR,//数据解析错误
        NET_ERROR,//网络连接错误
        TOKEEN_ERROR,//apptoken无效或异常。
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        LogUtils.i("Zuzu","ResultSubscriber-->exception");
        e.printStackTrace();
        if (NetWorkUtil.isNetworkConnected()) {
            onError(ERROR_TYPE.DATA_ERROR, "未知异常！");
        } else {
            onError(ERROR_TYPE.NET_ERROR, "网络连接超时，请检测您的网络状况！");
        }
        LogUtils.i("Zuzu", "request fail！");
    }

    @Override
    public void onNext(T t) {
        if (t == null) {
            LogUtils.i("Zuzu", "data_empty！");
            onError(ERROR_TYPE.DATA_EMPTY, "服务器异常！");
        } else if ("100002".equals(t.getErron()) || "100003".equals(t.getErron())) {
            String msg;
            if ("-1".equals(SharedPreUtil.getAppToken())) {
                msg = "您还没有登录，不能进行该操作！";
            } else {
                msg = "抱歉您账户已过期，请重新登录！";
            }
            onError(ERROR_TYPE.TOKEEN_ERROR, msg);
        } else if ("100000".equals(t.getErron())) {
            sucess();
        } else if ("-1200".equals(t.getErron())) {
            onError(ERROR_TYPE.NET_ERROR, t.getMsg());
        } else {
            onError(ERROR_TYPE.DATA_ERROR, t.getMsg());
        }
    }

    //请求成功
    protected abstract void sucess();
    //发生错误！
    protected abstract void onError(ERROR_TYPE type, String msg);
}