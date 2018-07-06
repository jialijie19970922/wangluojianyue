package com.example.administrator.sixgroupsofprojects.modle.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Process;

import com.example.administrator.sixgroupsofprojects.application.MyAppLication;

/**
 * Created by jlj on 2018/6/18 0018.
 */

public class SharedPreferencesUtil {
    //声明Sharedpreferenced对象
    private static SharedPreferences msp;
    private SharedPreferencesUtil() {}
    private static final SharedPreferencesUtil single = new SharedPreferencesUtil();
    //静态工厂方法
    public static SharedPreferencesUtil getInstance() {
        return single;
    }
    /**
     * 初始化sp存储
     */
    private static void initShared(){
        if(null == msp){
            msp = MyAppLication.getContext().getSharedPreferences("MyShared", Context.MODE_PRIVATE);
        }
    }
    public static void runOnUiThread(Runnable runnable){
        if(Process.myTid() == MyAppLication.getMyTid()){
            runnable.run();
        }else{
            MyAppLication.getMyHandler().post(runnable);
        }
    }
    // 存储字符串
    public static void setSharedString(String key,String value){
        initShared();
        boolean commit = msp.edit().putString(key, value).commit();
    }
    // 存储boolean
    public static void setSharedBoolean(String key,boolean value){
        initShared();
        boolean commit = msp.edit().putBoolean(key, value).commit();
    }
    // 存储int
    public static void setSharedInt(String key,int value) {
        initShared();
        boolean commit = msp.edit().putInt(key, value).commit();
    }
    // 获取字符串
    public static String getStringValue(String key){
        initShared();
        return msp.getString(key,"isNull");
    }
    // 获取boolean
    public static boolean getBooleanValue(String key){
        initShared();
        return msp.getBoolean(key,false);
    }
    // 获取int
    public static int getIntValue(String key){
        initShared();
        return msp.getInt(key,-1);
    }
    //清空sp
    public static void backLogin(){
        initShared();
        msp.edit().clear().commit();
    }
}