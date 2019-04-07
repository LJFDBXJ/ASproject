package com.manger.base_ativity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class BaseActivity extends Activity {

	public void starActivity(Class<?> targetClass) {// 普通目标跳转
		Intent intent = new Intent(this, targetClass);
		startActivity(intent);

	}

	public void startActivity(Class<?> targetClass, String index) {// 传递参数的跳转
		Intent intent = new Intent(this, targetClass);
		intent.putExtra("index", index);
		startActivity(intent);
	}

	public void startActivity(Class<?> targetClass, int index) {// 传递参数的跳转
		Intent intent = new Intent(this, targetClass);
		intent.putExtra("index", index);
		startActivityForResult(intent, 1);
	}

	public void startActivity(Class<?> targetClass, int inanimID, int outAnimID) {// 带动画的跳转
		Intent intent = new Intent(this, targetClass);
		startActivity(intent);
		overridePendingTransition(inanimID, outAnimID);
	}

	public void startAcitivty(Class<?> targetClass, int inAnimID,
							  int outAnimID, Bundle bundle) {
		Intent intent = new Intent(this, targetClass);
		intent.putExtras(bundle);
		startActivity(intent);
		overridePendingTransition(inAnimID, outAnimID);// 带动画的跳转
	}
	protected Handler mainHandler=new Handler(){
		public void handleMessage(android.os.Message msg){

			myHandleMessage(msg);

		};
	};
	protected void myHandleMessage(android.os.Message msg){

	}
}
