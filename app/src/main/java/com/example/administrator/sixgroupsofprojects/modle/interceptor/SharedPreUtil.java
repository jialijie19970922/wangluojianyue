package com.example.administrator.sixgroupsofprojects.modle.interceptor;

/**
 * Created by Administrator on 2018/6/11/011.
 */

public class SharedPreUtil {
    private static String appToken = "101";
    private static String source = "android";
    private static String appVersion = "101";

    public static String getAppToken() {
        return appToken;
    }
    public static String getAppsource() {
        return source;
    }
    public static String getAppVersion() {
        return appVersion;
    }
}
