package com.example.administrator.anew.Utils_Tool;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.appwidget.AppWidgetProviderInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.administrator.anew.R;
import com.example.administrator.anew.com.activity.MainActivity;

public class NewAppWidget extends AppWidgetProvider      {
Button button;

    @Override
    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        super.onRestored(context, oldWidgetIds, newWidgetIds);
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
        /**当App的大小被改变是会调用
         *
         */
        Bundle bundle=appWidgetManager.getAppWidgetOptions(R.layout.new_app_widget);
        AppWidgetProviderInfo bundl=appWidgetManager.getAppWidgetInfo(Bundle.CONTENTS_FILE_DESCRIPTOR);
        Toast.makeText(context,"LJFDBXJ-Widget的大小被改变",Toast.LENGTH_LONG).show();
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);
        Intent intent =new Intent(context, MainActivity.class);

//       views.setOnClickFillInIntent(this);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
            Toast.makeText(MyContext.getContext(),"onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)" +
                    "当Widget更新是被调用，首次创建也会被 调用",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        Toast.makeText(MyContext.getContext()," onEnabled(Context context) 当第一个Widget的实例被创建时，被触发，如果用户对同一个控件添加2次，本方法只被调用一次" +
                "",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
        Toast.makeText(MyContext.getContext(),"最后一个Widget的实例被删除",Toast.LENGTH_LONG).show();
    }
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Toast.makeText(MyContext.getContext(),"当Widget被删，此方法被调用",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Toast.makeText(MyContext.getContext(),"接收到任意广播时触发，比上述方法优先调用",Toast.LENGTH_LONG).show();

    }
}

