package com.example.administrator.sixgroupsofprojects.modle.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2018/7/5/005.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context cxt) {
        super(cxt, "liuzu", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库sql语句
        String sql = "create table user(id int,name varchar(20))";
        //执行创建数据库操作
        db.execSQL(sql);
    }

    /**
     * 升级数据库
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
