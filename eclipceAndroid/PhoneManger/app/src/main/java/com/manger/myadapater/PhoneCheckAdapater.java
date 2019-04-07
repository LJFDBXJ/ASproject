package com.manger.myadapater;

import com.manger.R;
import com.manger.base.PhoneCheck;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PhoneCheckAdapater extends MyBaseAdpat<PhoneCheck> {

	public PhoneCheckAdapater(List<PhoneCheck> list, Context context) {
		super(list, context);

	}

	@Override
	public View myView(int position, View convertView, ViewGroup parent) {
		ViewHoled holed;
		if (convertView == null) {
			holed = new ViewHoled();

			convertView = inflater.inflate(R.layout.inflate_phonecheck, null);

			holed.text1 =  convertView.findViewById(R.id.textcheck1);

			convertView.setTag(holed);

		} else {
			holed = (ViewHoled) convertView.getTag();
		}
		holed.text1.setText(list.get(position).getText1());

		return convertView;
	}

	class ViewHoled {
		TextView text1;

	}
}
