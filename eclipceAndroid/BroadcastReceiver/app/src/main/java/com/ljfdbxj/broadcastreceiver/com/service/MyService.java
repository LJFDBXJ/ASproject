package com.ljfdbxj.broadcastreceiver.com.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.text.format.Time;
import android.util.Log;

import com.ljfdbxj.broadcastreceiver.MainActivity;
import com.ljfdbxj.broadcastreceiver.R;
import com.ljfdbxj.broadcastreceiver.bottomActivity;

public class MyService extends Service {
    // 定义个一个Tag标签
    private static final String TAG = "MyService";

    @Override
    public void onCreate() {
        Log.i( TAG, "start onCreate~~~" );
        super.onCreate();
        Intent intent_next = new Intent( this, bottomActivity.class );

        PendingIntent pendingintent = PendingIntent.getActivity( this, 0,
                intent_next, 0 );// flags,标记延迟跳转

        // NotificationManger//通知管理，通过调用getSystemService来获得系统服务，是什么样的服务，通过上下文，调取具体的服务
        NotificationManager manager = (NotificationManager) getSystemService( Context.NOTIFICATION_SERVICE );
        Notification notification = new Notification.Builder( this )

                .setContentTitle( "本地通知" )
                .setContentText( "强大的内心" )
                .setWhen( System.currentTimeMillis() )
				.setSmallIcon(R.mipmap.ic_launcher)
                // 设置小的通知图标
                .setLargeIcon( BitmapFactory.decodeResource( getResources(), R.drawable.ic_launcher_background ) )
                // 设置大的图标与本地通知搭配
                .setContentIntent( pendingintent )
                .setAutoCancel(true)
                // 设置通知跳转的目标
                .setLights( Color.RED, 3000, 1000 )
//				.setStyle(new NotificationCompat.BigPictureStyle()
//				.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground)))// 设置最大的通知图标
                .setDefaults( Notification.DEFAULT_ALL ).build();

        manager.notify( 5, notification );//通过通知管理的对象来发送通知，将设置好的通知通知。
        startForeground( 5, notification );
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i( TAG, "StartCommand~~~" );
        return super.onStartCommand( intent, flags, startId );
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.i( TAG, "onStart~~~" );
        super.onStart( intent, startId );
    }

    @Override
    public void onDestroy() {
        Log.i( TAG, "start onDestroy~~~" );
        super.onDestroy();
    }


    @Override
    public IBinder onBind(Intent intent) {
        Log.i( TAG, "start IBinder~~~" );
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i( TAG, "start onUnbind~~~" );
        return super.onUnbind( intent );
    }

    // 这里我写了一个获取当前时间的函数，不过没有格式化就先这么着吧
    public String getSystemTime() {

        Time t = new Time();
        t.setToNow();
        return t.toString();
    }

    // 这里定义吧一个Binder类，用在onBind()有方法里，这样Activity那边可以获取到
    private MyBinder mBinder = new MyBinder();

    public class MyBinder extends Binder {

        MyService getService() {
            return MyService.this;
        }
    }

}