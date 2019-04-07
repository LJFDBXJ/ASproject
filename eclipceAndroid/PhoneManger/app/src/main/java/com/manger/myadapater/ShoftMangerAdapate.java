package com.manger.myadapater;

import com.manger.R;
import com.manger.base.ShoftMa;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ShoftMangerAdapate extends publicAdapte_base<ShoftMa> {

	public ShoftMangerAdapate(Context context) {
		super(context);
	}

	@Override
	public View myView(final int position, View convertView, ViewGroup parent) {
		ViewHold viewhole;
		if (convertView == null) {

			viewhole = new ViewHold();
			convertView = inflater.inflate(R.layout.inflate_shoftmanger, null);

			viewhole.shoftcheck = (CheckBox) convertView
					.findViewById(R.id.checkBox_shoft);
			viewhole.image = (ImageView) convertView
					.findViewById(R.id.imageView_shoft1);
			viewhole.text1 = (TextView) convertView
					.findViewById(R.id.textView_shoft1);
			viewhole.text2 = (TextView) convertView
					.findViewById(R.id.textView_shoft2);
			viewhole.text3 = (TextView) convertView
					.findViewById(R.id.textView_shoft3);

			convertView.setTag(viewhole);
		} else {
			viewhole = (ViewHold) convertView.getTag();

		}
		viewhole.text1.setText(list.get(position).getAppName());
		viewhole.text2.setText(list.get(position).getPackageName());
		viewhole.text3.setText(list.get(position).getVersionName());
		viewhole.image.setImageDrawable(list.get(position).getAppIcon());

		viewhole.shoftcheck
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						list.get(position).setCheck(isChecked);

					}
				});
		viewhole.shoftcheck.setChecked(list.get(position).isCheck());
		return convertView;
	}

	public class ViewHold {
		TextView text1;
		TextView text2;
		TextView text3;
		CheckBox shoftcheck;
		ImageView image;
	}
}
