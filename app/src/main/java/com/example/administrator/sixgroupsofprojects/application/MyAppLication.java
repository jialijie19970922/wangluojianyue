package com.example.administrator.sixgroupsofprojects.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

public class MyAppLication extends Application {

    private static Handler myHandler;
    private static Context context;
    private static int myTid;
    /**
     * 打开的activity
     **/
    private List<Activity> activities = new ArrayList<Activity>();
    /**
     * 应用实例
     **/
    private static MyAppLication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Fresco.initialize(this);
        myTid = Process.myTid();
        myHandler = new Handler();
        context = this;
        //异常处理
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        //注册rashHandler
//        crashHandler.init(getApplicationContext());

    }


    /**
     * 获得实例
     *
     * @return
     */
    public static MyAppLication getInstance() {
        return instance;
    }

    /**
     * 新建了一个activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 结束指定的Activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            this.activities.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 应用退出，结束所有的activity
     */
    public void exit() {
        for (Activity activity : activities) {
            if (activity != null) {
                activity.finish();
            }
        }
        System.exit(0);
    }

    /**
     * 关闭Activity列表中的所有Activity
     */
    public void finishActivity() {

        for (Activity activity : activities) {
            if (null != activity) {
                activity.finish();
            }
        }
        //杀死该应用进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }


    public static int getMyTid() {
        return myTid;
    }

    public static Handler getMyHandler() {
        return myHandler;
    }

    public static Context getContext() {
        return context;
    }

}
