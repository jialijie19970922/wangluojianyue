package com.example.administrator.sixgroupsofprojects.modle.callback;

public interface HttpUtilsCallback {
    void onSuccess(String str);
    void onFail(int errCode, String errMsg);
}
