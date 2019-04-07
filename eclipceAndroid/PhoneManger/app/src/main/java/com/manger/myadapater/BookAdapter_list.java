package com.manger.myadapater;

import java.util.List;

import com.manger.R;
import com.manger.base.PhoneClass;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BookAdapter_list extends MyBaseAdpat<PhoneClass> {

	public BookAdapter_list(List<PhoneClass> list, Context context) {
		super(list, context);
	}

	@Override
	public View myView(int position, View convertView, ViewGroup parent) {
		ViewHoled holed;
		if (convertView == null) {
			holed = new ViewHoled();

			convertView = inflater.inflate(R.layout.inflate_book_list, null);
			holed.text1 = (TextView) convertView.findViewById(R.id.booklist_1);
			holed.text2 = (TextView) convertView.findViewById(R.id.booklist_2);
			convertView.setTag(holed);

		} else {
			holed = (ViewHoled) convertView.getTag();
		}
		holed.text1.setText(list.get(position).getName());
		holed.text2.setText(list.get(position).getNumber());
		return convertView;
	}

	class ViewHoled {
		TextView text1;
		TextView text2;
	}
}
