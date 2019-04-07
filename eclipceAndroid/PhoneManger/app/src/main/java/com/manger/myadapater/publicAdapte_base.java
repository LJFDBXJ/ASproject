package com.manger.myadapater;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class publicAdapte_base<T> extends BaseAdapter {
	Context context;
	LayoutInflater inflater;

	List<T> list = new ArrayList<T>();

	public publicAdapte_base(Context context) {
		inflater = LayoutInflater.from(context);
		this.context = context;
	}

	public void addDatas(T a) {
		list.add(a);
	}

	public void addDatasFistClear(List<T> ts) {
		if (ts != null) {
			removeDatas();
			list.addAll(ts);
		}
	}

	public List<T> getDatas() {
		return list;
	}

	public void addDatas(List<T> lisr) {

		this.list = lisr;
	}

	public void removeDatas(int index) {
		if (index >= 0) {
			list.remove(index);
		}
	}

	public void removeDatas() {
		if (list.size() > 0 && list != null) {
			list.clear();
		}
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
