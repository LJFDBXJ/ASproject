package edu.feicui.newses.model.biz.parser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import edu.feicui.newses.entity.DBOpenHelper;
import edu.feicui.newses.entity.News;

/**
 * Created by Administrator on 2016/12/13.
 */

public class NewsDBManager {
    private static NewsDBManager dbManager;
    private DBOpenHelper openHelper;
    private NewsDBManager(Context context){
        openHelper=new DBOpenHelper(context);

    }
    public static NewsDBManager getDbManager(Context context){
        if (dbManager==null){
            synchronized (NewsDBManager.class){
                if (dbManager==null){
                    dbManager=new NewsDBManager(context);
                }
            }
        }
        return dbManager;
    }
    public void insertNews(News news){
        SQLiteDatabase db=openHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("nid",news.getNid());
        values.put("title",news.getTitle());
        values.put("summary",news.getSummary());
        values.put("icon",news.getIcon());
        values.put("link",news.getLink());
        values.put("type",news.getType());
        db.insert("news",null,values);
        db.close();
    }
    public long getCount(){
        SQLiteDatabase db=openHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select count(*) from news",null);
        long len=0;
        if (cursor.moveToFirst()){
            len=cursor.getLong(0);
        }
        cursor.close();
        db.close();
       return len;
    }
    public ArrayList<News> queryNews(int count, int offset){
        ArrayList<News> newsList=new ArrayList<News>();
        SQLiteDatabase db=openHelper.getWritableDatabase();
        String sql="select * from news order by _id desc limit "+count+" offset "+offset;
        Cursor cursor=db.rawQuery(sql,null);
        if (cursor.moveToFirst()) {
            do {
                int nid = cursor.getInt(cursor.getColumnIndex("nid"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String summary = cursor.getString(cursor.getColumnIndex("summary"));
                String icon = cursor.getString(cursor.getColumnIndex("icon"));
                String link = cursor.getString(cursor.getColumnIndex("link"));
                int type = cursor.getInt(cursor.getColumnIndex("type"));
                String stamp=cursor.getString(cursor.getColumnIndex("stamp"));;
                News news = new News(type, nid,stamp,icon,title,summary,link);
                newsList.add(news);
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return newsList;
    }


}
