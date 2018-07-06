package com.example.administrator.sixgroupsofprojects.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.administrator.sixgroupsofprojects.R;
import com.example.administrator.sixgroupsofprojects.presenter.MainPresenter;
import com.example.administrator.sixgroupsofprojects.utils.CommonUtil;
import com.example.administrator.sixgroupsofprojects.view.interfaces.IMainView;

import java.util.HashMap;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainView,View.OnClickListener{


    private EditText mobile;
    private EditText password;
    private EditText password_sure;

    @Override
    void initView() {
        password = findViewById(R.id.password);
        mobile = findViewById(R.id.mobile);
        password_sure = findViewById(R.id.password_sure);
        findViewById(R.id.register).setOnClickListener(this);
    }

    @Override
    void initData() {

    }

      @Override
    MainPresenter setPresenter() {
        return new MainPresenter();
    }

    @Override
    int setChildContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(String string) {
        Log.e("myMessage"," = "+string);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                String phoneNum = mobile.getText().toString();
                String passwrd = password.getText().toString();
                String passwrd_sure = password_sure.getText().toString();
                if(!CommonUtil.isMobileNO(phoneNum)) {
                    Toast.makeText(MainActivity.this,getResources().getString(R.string.wrong_mobile_num),Toast.LENGTH_SHORT).show();
                    return;
                }

                if(passwrd == null || "".equals(passwrd)) {
                    Toast.makeText(MainActivity.this,getResources().getString(R.string.wrong_password),Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!passwrd.equals(passwrd_sure)) {
                    Toast.makeText(MainActivity.this,getResources().getString(R.string.wrong_password_diff),Toast.LENGTH_SHORT).show();
                    return;
                }


                if(presenter != null) {
                    String path = "user/reg";
                    HashMap<String,String> hashMap = new HashMap<>();
                    hashMap.put("mobile",phoneNum);
                    hashMap.put("password",passwrd);
                    presenter.register(path,hashMap);
                }

                break;
        }
    }
}
