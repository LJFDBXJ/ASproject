package com.fuicuiedu.xc.videonew;

import android.app.Application;

import com.fuicuiedu.xc.videonew.commons.ToastUtils;

/**
 *   所有界面共享吐司的工具类，初始化吐司
 */

public class VideoNewsApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化吐丝工具类
        ToastUtils.init(this);

    }

}
