package com.manger.manger;

import com.manger.R;
import com.manger.activity.HomeActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;
public class Notifiction_Manger {
	private static NotificationManager notiManager=null;
	private  static Notification notifi=null;
	public TextView notifiction;

	static View view;

	public static boolean isOpenNotification(Context context){
		SharedPreferences shared=context.getSharedPreferences("nifi",Context.MODE_PRIVATE);
		return shared.getBoolean("open", false);

	}

	public static void setOpenNotifivation(Context context,boolean open){
		SharedPreferences shared=context.getSharedPreferences("nifi",Context.MODE_PRIVATE);
		Editor editor=shared.edit();
		editor.putBoolean("open", open);
		editor.commit();

	}

	public static void showAppNotifiction(Context context){
		if(notifi==null){
			notifi=new Notification();
		}
		notifi.flags=Notification.FLAG_AUTO_CANCEL;//FLAG_AUTO_CANCEL
		notifi.icon=R.drawable.aa;//设置图标
		notifi.tickerText="新通知";//设置标题
		notifi.when=System.currentTimeMillis();//设置当前的时间
		notifi.defaults=Notification.DEFAULT_SOUND;//设置默认的系统声音

		RemoteViews remote=new RemoteViews(context.getPackageName(), R.layout.inflater_notifictionsingle);
		notifi.contentView=remote;

		Intent intent =new Intent(context,HomeActivity.class);
		PendingIntent pendin=PendingIntent.getActivity(context, 1, intent, 1);
		notifi.contentIntent=pendin;//设置跳转的方式

		if(notiManager==null){
			notiManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		}
		notiManager.notify(1, notifi);


	}
	public static void cancelAppIconNotification(Context context){
		if (notiManager==null) {
			notiManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		}
		notiManager.cancel(1);
	}
}
