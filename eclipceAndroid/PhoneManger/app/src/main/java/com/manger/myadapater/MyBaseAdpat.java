package com.manger.myadapater;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MyBaseAdpat<T> extends BaseAdapter {
	List<T> list;
	LayoutInflater inflater;

	public MyBaseAdpat(List<T> list, Context context) {// 当初始化适配器的时候把需要的list传入，并将需要适配的上下文传入

		this.list = list;
		this.inflater = LayoutInflater.from(context);

	}

	public MyBaseAdpat(Context context) {

		inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		return myView(position, convertView, parent);

	}

	public abstract View myView(int position, View convertView, ViewGroup parent);
}
