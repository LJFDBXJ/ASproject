package com.manger.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.manger.R;
import com.manger.base.ShoftMa;
import com.manger.manger.APPManger;
import com.manger.myadapater.ShoftMangerAdapate;
import com.manger.tools.Actionbar;
import com.manger.tools.ClickBaseAcitivity;

public class Shoftmanger_listview extends ClickBaseAcitivity implements
		Runnable, OnItemClickListener, OnClickListener {
	ListView listview;
	ShoftMangerAdapate sma;
	LayoutInflater inflater;
	List<ShoftMa> list;
	CheckBox shofall;
	Button butto;
	String str;
	ProgressBar pro;
	APPManger Shoft;

	String[] delete = { "OPEN", "DELETE" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.listviewshoft_manger_);
		listview =  findViewById(R.id.listView_shoft1);// ListView
		shofall =  findViewById(R.id.checkall_shoftBox1);// 全选
		butto =  findViewById(R.id.button_delete1);// 删除
		pro =  findViewById(R.id.progressBar1);// 进度
		sma = new ShoftMangerAdapate(this);
		str = getIntent().getStringExtra("index");// 获取intent的参数
		Shoft = APPManger.getAPPManger(this);
		aysngetData();
		list = new ArrayList<>();
		list.add(new ShoftMa());
		sma.addDatas(list);
		listview.setAdapter(sma);
		butto.setOnClickListener(this);
		listview.setOnItemClickListener(this);
		shofall.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
										 boolean isChecked) {
				ArrayList<ShoftMa> array = (ArrayList<ShoftMa>) sma.getDatas();
				if (isChecked == true) {

					for (int i = 0; i < array.size(); i++) {

						array.get(i).setCheck(isChecked);

						sma.addDatas(array);
						listview.setAdapter(sma);

					}

				} else {
					for (int i = 0; i < array.size(); i++) {

						array.get(i).setCheck(isChecked);

						sma.addDatas(array);
						listview.setAdapter(sma);
					}
				}

			}
		});
		MyOnActionListener();
	}

	/**
	 * 更新方法
	 */
	@Override
	public void run() {
		Shoft.getAppListAll(str, new APPManger.MyDataLinister() {
			@Override
			public void updataOne(ShoftMa app) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
					}
				});
			}

			@Override
			public void updataAll(final ArrayList<ShoftMa> list) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						sma.addDatas(list);

						sma.notifyDataSetChanged();
						pro.setVisibility(View.GONE);
					}
				});
			}
		});

	}

	private void aysngetData() {
		pro.setVisibility(View.VISIBLE);
		new Thread(this).start();
	}

	public void MyOnActionListener() {
		@SuppressLint("WrongViewCast") Actionbar bar =  findViewById(R.id.action_bar);
		Intent intent = getIntent();
		String a = intent.getStringExtra("index");
		bar.initActionBar(a, R.drawable.ic_laun, R.drawable.ic_child_configs,
				new OnClickListener() {
					@Override
					public void onClick(View v) {

						switch (v.getId()) {
							case R.id.image_left:
								starActivity(AboutActivity.class);

								break;
							case R.id.image_right:
								starActivity(SetActivity.class);
								break;

							default:
								break;
						}
					}
				});
	}

	/**
	 * listView 的单项监听
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Warning!");
		builder.setIcon(R.drawable.delete);
		final ShoftMa shoftma = (ShoftMa) sma.getItem(arg2);
		builder.setNegativeButton("are you sure?you whant to delete this app?",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}
				});
		builder.setItems(delete, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (which == 0) {// 打开

					Intent intent = shoftma.getIntent();
					if (intent != null
							& !"com.an".equals(shoftma.getPackageName())) {
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(intent);
					}
				} else if (which == 1) {// 卸载

					Intent intent = new Intent();
					Uri uri = Uri.parse("package:" + shoftma.getPackageName());
					intent.setData(uri);
					intent.setAction(Intent.ACTION_DELETE);// 跳转到系统提供卸载界面

					if (!"com.an".equals(shoftma.getPackageName())) {
						startActivityForResult(intent, 110);
					}
				}
			}
		});
		builder.show();

	}

	/**
	 * 全选卸载
	 */
	@Override
	public void onClick(View v) {
		ArrayList<ShoftMa> list1 = (ArrayList<ShoftMa>) sma.getDatas();
		for (int i = 0; i < list1.size(); i++) {
			if (list1.get(i).isCheck()

					&& !"com.an".equals(list1.get(i).getPackageName())) {
				Intent intent = new Intent();
				Uri uri = Uri.parse("package:" + list1.get(i).getPackageName());
				intent.setData(uri);
				intent.setAction(Intent.ACTION_DELETE);
				startActivityForResult(intent, 110);

			}
		}

	}

	@Override
	public void startActivityForResult(Intent intent, int requestCode,
									   Bundle options) {
		// TODO Auto-generated method stub
		super.startActivityForResult(intent, requestCode, options);
		if (requestCode == 110) {
			aysngetData();
		}
	}
}