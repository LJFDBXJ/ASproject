package com.manger.activity;

import com.manger.R;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

public class LogoActivity extends Activity {
    ImageView image;


    /**
     * //MODE_APPEND文件创建模式，用于打开文件（String，int），如果文件已经存在，然后写数据到现有文件的末尾
     * <p>
     * MODE_ENABLE_WRITE_AHEAD_LOGGING 打开数据库时，默认情况下启用写日志记录，打开数据库打开标记。
     * <p>
     * MODE_PRIVATE 文件创建模式默认的模式，创建的文件只能调用应用程序访问所有程序共享的ID.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_logo );

        image = findViewById( R.id.logo_View1 );

        AnimationDrawable anim = (AnimationDrawable) image.getDrawable();
        anim.start();


        int duration = 0;
        for (int i = 0; i < anim.getNumberOfFrames(); i++) {
            duration += anim.getDuration( i );

        }

        Handler handler = new Handler();
        handler.postDelayed( new Runnable() {
            public void run() {

                // 此处调用第二个动画播放方法
                Intent intent = new Intent( LogoActivity.this, HomeActivity.class );
                startActivity( intent );
                finish();
            }
        }, duration );

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

}
