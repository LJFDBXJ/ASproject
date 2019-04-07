package com.example.administrator.anew.Utils_Tool;

import android.app.Application;

/**
 * Created by Administrator on 2017/2/4.
 */

public class MyContext extends Application {
    private static android.content.Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    public static android.content.Context getContext(){
        return context;
    }
}
