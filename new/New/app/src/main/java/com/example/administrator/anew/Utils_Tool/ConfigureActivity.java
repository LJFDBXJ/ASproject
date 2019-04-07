package com.example.administrator.anew.Utils_Tool;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.administrator.anew.R;
public class ConfigureActivity extends Activity {

    private static final String PREFS_NAME = "com.example.administrator.anew.Utils_Tool.NewAppWidget_2";
    private static final String PREF_PREFIX_KEY = "appwidget_";
    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    EditText mAppWidgetText;

    public ConfigureActivity() {
        super();
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        /**
         *   Set the result to CANCELED.  This will cause the widget host to cancel
         *   out of the widget placement if the user presses the back button.
         *   将结果设置为取消。这将导致部件主机取消
         *  出的部件的位置如果用户按下后面的按钮。
         */
        setResult(RESULT_CANCELED);
        setContentView(R.layout.new_app_widget_2_configure);
        mAppWidgetText = (EditText) findViewById(R.id.appwidget_text);
        findViewById(R.id.add_button).setOnClickListener(mOnClickListener);
        // Find the widget id from the intent.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }
        /**
         * If this activity was started with an intent without an app widget ID, finish with an error.
         * 如果该活动以没有应用程序部件ID的意图启动，则以错误结束。
         */
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }
        mAppWidgetText.setText(loadTitlePref(ConfigureActivity.this, mAppWidgetId));
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            final Context context = ConfigureActivity.this;
            /**
             * When the button is clicked, store the string locally
             * 单击按钮时，将字符串存储在本地
             */
            String widgetText = mAppWidgetText.getText().toString();
            saveTitlePref(context, mAppWidgetId, widgetText);
            /**
             *  It is the responsibility of the configuration activity to update the app widget
             *  这是配置活动的责任，以更新应用程序部件
             */
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            NewAppWidget.updateAppWidget(context, appWidgetManager, mAppWidgetId);

            /**
             * Make sure we pass back the original appWidgetId
             */
            Intent resultValue = new Intent();
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
            setResult(RESULT_OK, resultValue);
            finish();
        }
    };


    // Write the prefix to the SharedPreferences object for this widget
    static void saveTitlePref(Context context, int appWidgetId, String text) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.putString(PREF_PREFIX_KEY + appWidgetId, text);
        prefs.apply();
    }

    // Read the prefix from the SharedPreferences object for this widget.
    // If there is no preference saved, get the default from a resource
    static String loadTitlePref(Context context, int appWidgetId) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        String titleValue = prefs.getString(PREF_PREFIX_KEY + appWidgetId, null);
        if (titleValue != null) {
            return titleValue;
        } else {
            return context.getString(R.string.appwidget_text);
        }
    }

    static void deleteTitlePref(Context context, int appWidgetId) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.remove(PREF_PREFIX_KEY + appWidgetId);
        prefs.apply();
    }


}

