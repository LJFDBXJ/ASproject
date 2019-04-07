package edu.feicui.newses.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import edu.feicui.newses.R;
import edu.feicui.newses.common.LogUtil;

/**
 * 父类 activity 用来调试打印 activity 生命周期和节目的进入和退出动画
 */

public class MyBaseActivity extends Activity {
    protected int screen_w, screen_h;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screen_w = getWindowManager().getDefaultDisplay().getWidth();
        screen_h = getWindowManager().getDefaultDisplay().getHeight();

    }

    protected void onStart() {
        super.onStart();
        LogUtil.d(LogUtil.TAG, getClass().getSimpleName() + "onStart()");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.d(LogUtil.TAG, getClass().getSimpleName() + "onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d(LogUtil.TAG, getClass().getSimpleName() + "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d(LogUtil.TAG, getClass().getSimpleName() + "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d(LogUtil.TAG, getClass().getSimpleName() + "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d(LogUtil.TAG, getClass().getSimpleName() + "onDestroy()");
    }

    //通过 class 名字进行界面跳转并带 Bundle，Uri 数据
    public void openActivity(Class<?> pclass, Bundle bundle, Uri uri) {
        Intent intent = new Intent(this, pclass);
        if (bundle != null)
            intent.putExtras(bundle);
        if (uri != null)
            intent.setData(uri);
        startActivity(intent);
    }

    //通过 class 名字进行界面跳转只带 Bundle 数据
    public void openActivity(Class<?> pClass, Bundle bundle) {
        openActivity(pClass, bundle, null);
    }

    //通过 class 名字进行界面跳转
    public void openActivity(Class<?> pClass) {
        openActivity(pClass, null, null);
    }
    //通过activity字符串进行界面跳转
    public void openActivity(String action) {
        openActivity(action);
    }

    //通过 action 字符串进行界面跳转，只带 Bundle 数据
    public void openActivity(String activity, Bundle bundle) {
        openActivity(activity, bundle);
    }

    //通过 action 字符串进行界面跳转，并带 Bundle 和 Url 数据
    public void openActivity(String action, Bundle bundle, Uri uri) {
        Intent intent = new Intent(action);
        if (bundle != null)
            intent.putExtras(bundle);
        if (uri != null)
            intent.setData(uri);
        startActivity(intent);
    }

    /**************
     * 公共功能封装
     ***************************/
    private Toast toast;

    public void showToast(int resId) {
        showToast(getString(resId));
    }

    public void showToast(String msg) {
        if (toast == null)
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setText(msg);
        toast.show();
    }
    public void actions(int left, String contant, View.OnClickListener clickleft){
       ImageView iv_left=(ImageView)findViewById(R.id.iv_left);
//       TextView tv_conter=(TextView)findViewById(R.id.tv_conter);
       iv_left.setOnClickListener(clickleft);
    }
    
}
