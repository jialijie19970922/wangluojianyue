package com.example.administrator.sixgroupsofprojects.presenter;


import com.example.administrator.sixgroupsofprojects.view.interfaces.IBaseView;

public class BasePresenter<V extends IBaseView> {

    private V mIBaseView;
    public void attacheView(V iBaseView) {
        this.mIBaseView = iBaseView;
    }

    public V getView(){
        return mIBaseView;
    }

    public void detachView() {
        mIBaseView = null;
    }
}
