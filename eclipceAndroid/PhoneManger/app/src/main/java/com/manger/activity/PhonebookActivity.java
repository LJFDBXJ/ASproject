package com.manger.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.manger.R;
import com.manger.base.PhoneClass;
import com.manger.db.DbReader;
import com.manger.db.FielCopy;
import com.manger.myadapater.BookAdapter;
import com.manger.tools.ClickBaseAcitivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class PhonebookActivity extends ClickBaseAcitivity implements
		OnItemClickListener {

	ListView listview;
	BookAdapter myadpter;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phonebook);
		listview = findViewById(R.id.listview_book);

		if (!DbReader.idExit()) {// 查看文件是否已存在，如果没有则复制

			String path = "phone" + File.separator + "commonnum.db";
			String topath = DbReader.telFile.getAbsolutePath();
			FielCopy.copypath(this, path, topath);

		}
		List<PhoneClass> list = new ArrayList<PhoneClass>();
		list.add(new PhoneClass("主机拨号", 0));
		list.addAll(DbReader.getAllDate());

		myadpter = new BookAdapter(list, this);
		listview.setAdapter(myadpter);
		listview.setOnItemClickListener(this);

		MyOnClickListener();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		startActivity(PhoneListActivity.class, arg2);

	}
}
