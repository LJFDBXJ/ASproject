package com.manger.activity;

import com.manger.R;

import android.os.Bundle;
import android.app.Activity;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

import com.manger.manger.Notifiction_Manger;

public class SetActivity extends Activity implements OnCheckedChangeListener {

	ToggleButton open, notifiction, messager;
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set);

		//注册
		open = (ToggleButton) findViewById(R.id.open);
		notifiction = (ToggleButton) findViewById(R.id.notifiction);
		messager = (ToggleButton) findViewById(R.id.messager);

		notifiction.setChecked(Notifiction_Manger.isOpenNotification(getApplicationContext()));
		//监听
		notifiction.setOnCheckedChangeListener(this);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if(isChecked){
			Notifiction_Manger.showAppNotifiction(getApplicationContext());
		}
		else {
			Notifiction_Manger.cancelAppIconNotification(getApplicationContext());
		}
	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
		Notifiction_Manger.setOpenNotifivation(getApplicationContext(), notifiction.isChecked());
	}
}
