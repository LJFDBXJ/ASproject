package com.example.administrator.anew.dao;

/**
 * Created by Administrator on 2016/12/13.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.anew.Utils_Tool.NewsBase;

import java.util.ArrayList;
/**数据库管理类*/
public class NewsDBManager {
    //单例模式
    private static NewsDBManager dbManager;
    private  DBOpenHelper helper;
    private NewsDBManager(Context context){
        helper=new DBOpenHelper(context);
    }
    //同步锁
    public static NewsDBManager getNewsDBManager(Context context){
        if (dbManager==null){
            synchronized (NewsDBManager.class){
                if (dbManager==null){
                    dbManager=new NewsDBManager(context);
                }
            }
        }
        return dbManager;
    }
    /**添加*/
    public void insertNews(NewsBase news){
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
//        values.put("nid", news.);

        values.put("title", news.getTitlehead());

        values.put("summary", news.getTitlebody());
        values.put("stamp",news.getMytime());
        values.put("icon", news.getImage());
        values.put("link", news.getMylink());
//        values.put("type", news.getType());
        db.insert("news", null, values);
        db.close();
    }
    /**数据数量*/
    public long getCount(){
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select count(*) from news",null);
        long len = 0;
        if (cursor.moveToFirst()) {
            len = cursor.getLong(0);
        }
        cursor.close();
        db.close();
        return len;
    }
    /**查询数据*/
    public ArrayList<NewsBase> queryNews(int count, int offset){
        ArrayList<NewsBase> newsList=new ArrayList<>();
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="select * from news order by _id desc limit "+count+" offset "+offset;
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                int nid = cursor.getInt(cursor.getColumnIndex("nid"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String summary = cursor.getString(cursor.getColumnIndex("summary"));
                String icon = cursor.getString(cursor.getColumnIndex("icon"));
                String link = cursor.getString(cursor.getColumnIndex("link"));
                int type = cursor.getInt(cursor.getColumnIndex("type"));
                String stamp=cursor.getString(cursor.getColumnIndex("stamp"));
//                String titlehead, String titlebody, String mytime, String image, String mylink)
                NewsBase news = new NewsBase(title,summary,stamp,icon,link);
                newsList.add(news);
            }while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return newsList;
    }
}
