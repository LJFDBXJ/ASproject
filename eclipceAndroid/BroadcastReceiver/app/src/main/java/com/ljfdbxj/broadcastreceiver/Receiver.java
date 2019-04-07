package com.ljfdbxj.broadcastreceiver;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import android.widget.Toast;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getAction();
        String a = "4564";
        if (name.equals( "haha" )) {
//            Intent intent_next = new Intent( context, bottomActivity.class );
//
////
//            PendingIntent pendin = PendingIntent.getActivity( context, 1, intent_next, PendingIntent.FLAG_NO_CREATE );
//            NotificationManager manger = (NotificationManager) context.getSystemService( context.NOTIFICATION_SERVICE );//获取通知的管理
//            Notification notifi = new NotificationCompat.Builder( context ).setSmallIcon( R.drawable.ic_launcher_background ).setPriority( NotificationCompat.PRIORITY_MAX ).build();
//
//            notifi.contentIntent = pendin;//设置跳转的方式
//            notifi.defaults = Notification.DEFAULT_SOUND;//设置默认的系统声音
//            notifi.when = System.currentTimeMillis();//设置当前的时间
//            notifi.visibility = Notification.BADGE_ICON_SMALL;
//            notifi.tickerText = "第二次";//设置标题
//            notifi.contentView = new RemoteViews( context.getPackageName(), R.layout.activity_next );
//            manger.notify( 1, notifi );//通过管理类发送通知。
            NotificationManager manager = (NotificationManager) context.getSystemService( Context.NOTIFICATION_SERVICE );

            Intent intent_next = new Intent( context, bottomActivity.class );

            PendingIntent pendingintent = PendingIntent.getActivity( context, 0,
                    intent_next, 0 );// flags,标记延迟跳转

            // NotificationManger//通知管理，通过调用getSystemService来获得系统服务，是什么样的服务，通过上下文，调取具体的服务
            Notification notification = new Notification.Builder( context )

                    .setContentTitle( "本地通知" )
                    .setContentText( "强大的内心" )
                    .setAutoCancel(true)
                    .setWhen( System.currentTimeMillis() )
				.setSmallIcon(R.mipmap.ic_launcher)
                    // 设置小的通知图标
                    .setLargeIcon( BitmapFactory.decodeResource(context. getResources(), R.mipmap.ic_launcher) )
                    // 设置大的图标与本地通知搭配
                    .setContentIntent( pendingintent )
                    // 设置通知跳转的目标
                    .setLights( Color.RED, 3000, 1000 )
//				.setStyle(new NotificationCompat.BigPictureStyle()
//				.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground)))// 设置最大的通知图标
                    .setDefaults( Notification.DEFAULT_ALL ).build();

            assert manager != null;
            manager.notify( R.layout.activity_main, notification );//通过通知管理的对象来发送通知，将设置好的通知通知。
////           context.startForeground( 5, notification );
//            Toast.makeText( context, a, Toast.LENGTH_SHORT )
//                    .show();
        }
    }


}

