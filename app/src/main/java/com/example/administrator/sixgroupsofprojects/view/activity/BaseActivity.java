package com.example.administrator.sixgroupsofprojects.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.sixgroupsofprojects.application.MyAppLication;
import com.example.administrator.sixgroupsofprojects.presenter.BasePresenter;
import com.example.administrator.sixgroupsofprojects.view.interfaces.IBaseView;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {

    public P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyAppLication.getInstance().addActivity(this);
        setContentView(setChildContentView());
        initView();
        initBaseData();
        initData();
    }

    private void initBaseData(){
        presenter = setPresenter();
        if (presenter!=null) {
            presenter.attacheView(this);
        } else {
            try {
                throw new Exception("老兄 prenter 没有设置 请在您的Activity 创建 presenter");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    };

    abstract void initView();
    abstract void initData();
    abstract P setPresenter();
    abstract int setChildContentView();
}
