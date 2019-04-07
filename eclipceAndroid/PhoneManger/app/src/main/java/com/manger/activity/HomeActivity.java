package com.manger.activity;

import java.util.List;
import com.manger.R;
import com.manger.base.Speed;
import com.manger.manger.APPManger;
import com.manger.manger.PhoneManager;
import com.manger.tools.ClickBaseAcitivity;
import com.manger.tools.HomeView;

import android.os.Bundle;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends ClickBaseAcitivity implements OnClickListener {
	TextView spead_1, soft_mang2, ponecheck_5, book_4, file_3, rubbish6,
			homespeed;
	HomeView homeview;
	ImageView imageview;
	PhoneManager phonemanger;
	APPManger appmanger;
	ProgressBar pro;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.include_home);
		idtest();
		MyOnClickListener();

	}

	public void idtest() {
		spead_1 = findViewById(R.id.spead_1);
		soft_mang2 =  findViewById(R.id.fiel_manger2);
		ponecheck_5 =  findViewById(R.id.phone_book_5);
		book_4 =  findViewById(R.id.app_recommend_4);
		file_3 =  findViewById(R.id.message_send_3);
		rubbish6 =findViewById(R.id.rubbish_clesr6);
		homespeed =findViewById(R.id.texthome_speed1);
		homeview = findViewById(R.id.homeview);
		spead_1.setOnClickListener(this);
		soft_mang2.setOnClickListener(this);
		ponecheck_5.setOnClickListener(this);
		book_4.setOnClickListener(this);
		file_3.setOnClickListener(this);
		rubbish6.setOnClickListener(this);
		homespeed.setOnClickListener(this);

		appmanger = APPManger.getAPPManger(this);
		phonemanger = PhoneManager.getPhoneManager(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.spead_1:
				Intent intent = new Intent(HomeActivity.this, SpeedActivity.class);
				startActivity(intent);
				break;
			case R.id.fiel_manger2:
				Intent intent2 = new Intent(HomeActivity.this,
						FileMangerActivity_one.class);
				startActivity(intent2);
				break;
			case R.id.phone_book_5:
				Intent intent3 = new Intent(HomeActivity.this,
						PhonebookActivity.class);
				startActivity(intent3);
				break;
			case R.id.app_recommend_4:
				Intent intent4 = new Intent(HomeActivity.this,
						SoftMsgActivity.class);
				startActivity(intent4);
				break;
			case R.id.message_send_3:
				Intent intent5 = new Intent(HomeActivity.this,
						PhoneCheckActivity.class);
				startActivity(intent5);
				break;
			case R.id.rubbish_clesr6:
				Intent intent6 = new Intent(HomeActivity.this,
						RubbishActivity.class);
				startActivity(intent6);
				break;
			case R.id.texthome_speed1:
				List<Speed> list = appmanger.getRunAllApp();
				for (int i = 0; i < list.size(); i++) {
					appmanger.killMyRunApp(list.get(i).getPackagename());
				}

				getMemory();
				break;
			default:
				break;
		}

	}

	private void getMemory() {
		long free = phonemanger.getMemoryManager().getFreeMemory();
		long total = phonemanger.getMemoryManager().getTotalMemory();

		final int in = (int) ((double) free / total * 100);
		int in1 = (int) ((double) free / total * 360);

		if (in < 50) {
			Toast toast = Toast.makeText(HomeActivity.this, "您的手机以处于最佳状态",
					Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, -350);
			toast.show();
		} else if (in > 50) {
			Toast toast = Toast.makeText(HomeActivity.this, "您的手机还需继续清理",
					Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 350);
			toast.show();
		}
		homespeed.setText(in + "%");
		homeview.go(in1);
	}
}