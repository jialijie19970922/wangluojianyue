package com.example.administrator.sixgroupsofprojects.modle.db;

import android.content.Context;

/**
 * 按需求自己写
 * Created by Administrator on 2018/7/5/005.
 */

public class Dao {
    DatabaseHelper helper = null;
    public Dao(Context cxt) {
        helper = new DatabaseHelper(cxt);
    }

    /**
     * 增
     */
    public void add(String s){

    }
    /**
     * 删
     */
    public void delete(int id){

    }
    /**
     * 改
     */
    public void upData(){

    }
    /**
     * 查
     */
    public void select(){

    }
}
