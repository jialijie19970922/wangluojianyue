package com.example.administrator.sixgroupsofprojects.presenter;

import android.util.Log;


import com.example.administrator.sixgroupsofprojects.modle.callback.HttpUtilsCallback;
import com.example.administrator.sixgroupsofprojects.modle.http.HttpUtils;
import com.example.administrator.sixgroupsofprojects.view.interfaces.IMainView;

import java.util.HashMap;


public class MainPresenter extends BasePresenter<IMainView> {

    private final HttpUtils httpUtils;

    public MainPresenter() {
        httpUtils = HttpUtils.getInstance();
    }

    public void register(String path,HashMap<String, String> hashMap) {
//        String path = "";
//        HashMap<String, String> hashMap = new HashMap<>();
        httpUtils.doPost(path, hashMap, new HttpUtilsCallback() {
            @Override
            public void onSuccess(String str) {
                Log.e("myMessage","== "+str);
                //
            }

            @Override
            public void onFail(int errCode, String errMsg) {

            }
        });
        //getView().onSuccess(data);
    }

}
