package com.manger.myadapater;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.manger.R;
import com.manger.base.PhoneClass;

public class BookAdapter extends MyBaseAdpat<PhoneClass> {

	public BookAdapter(List<PhoneClass> list, Context context) {

		super(list, context);

	}

	@Override
	public View myView(int position, View convertView, ViewGroup parent) {

		ListHolder holder;

		if (convertView == null) {

			holder = new ListHolder();
			convertView = inflater.inflate(R.layout.inflate_phone_book, null);
			holder.imager = (ImageView) convertView
					.findViewById(R.id.image_View1);
			holder.tet = (TextView) convertView
					.findViewById(R.id.TextView_singer1);
			convertView.setTag(holder);

		} else {
			holder = (ListHolder) convertView.getTag();
		}

		holder.imager.setImageResource(R.drawable.ic_arrows_right);
		holder.tet.setText(list.get(position).getName());
		return convertView;
	}

	class ListHolder {
		ImageView imager;
		TextView tet;
	}
}
