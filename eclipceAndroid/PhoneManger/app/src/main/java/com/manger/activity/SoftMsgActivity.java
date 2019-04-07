package com.manger.activity;

import com.manger.R;
import com.manger.manger.SystemManger;
import com.manger.tools.ClickBaseAcitivity;
import com.manger.tools.MyView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SoftMsgActivity extends ClickBaseAcitivity {
	TextView textall, textsys, textuse;
	MyView maview;
	TextView use, unuse, num;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shoftmanger);
		maview = findViewById(R.id.go);
		use =  findViewById(R.id.textView_yiyong6);
		unuse =  findViewById(R.id.textView_weiyong8);
		num = findViewById(R.id.textView_zongde7);

		textall = findViewById(R.id.textView_all3);
		textsys = findViewById(R.id.textView_system4);
		textuse =  findViewById(R.id.textView_user5);

		data();
		MyOnClickListener();

	}

	public void all(View view) {
		switch (view.getId()) {
			case R.id.textView_all3:
				startActivity(Shoftmanger_listview.class, "所有应用");

				break;
			case R.id.textView_system4:
				startActivity(Shoftmanger_listview.class, "系统应用");
				break;
			case R.id.textView_user5:
				startActivity(Shoftmanger_listview.class, "用户应用");
				break;
			default:
				break;
		}

	}

	public void data() {
		int a = (int) ((double) SystemManger.getSelfAvailableSize()
				/ SystemManger.getSize() * 360);

		maview.go(100);
		String str = (SystemManger.getSize() / 1024 / 1024 / 1024) + "" + "G";
		num.setText(str);// 总内存
		String str1 = ((SystemManger.getSize() - SystemManger
				.getSelfAvailableSize()) / 1024 / 1024 / 1024) + "" + "G";
		use.setText(str1);// 已使用
		String str2 = SystemManger.getSelfAvailableSize() + "";
		unuse.setText(str2);// 已使用
		// unuse.setText(text);//未使用内存
	}
}
