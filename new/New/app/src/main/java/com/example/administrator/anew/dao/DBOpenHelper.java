package com.example.administrator.anew.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/12/13.
 */

public class DBOpenHelper extends SQLiteOpenHelper{
    public DBOpenHelper(Context context) {
        super(context,"newsdb.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table news (" +
                "_id integer primary key autoincrement," +
                "nid integer," +
                "title text," +
                "summary text," +
                "icon text," +
                "link text," +
                "type integer," +
                "stamp text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
