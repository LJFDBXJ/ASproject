package com.manger.myadapater;

import com.manger.R;
import com.manger.base.Speed;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class Speed_adapate extends publicAdapte_base<Speed> {

	public Speed_adapate(Context context) {
		super(context);
	}

	@Override
	public View myView(final int position, View convertView, ViewGroup parent) {

		ViewHold viewhold;
		if (convertView == null) {
			viewhold = new ViewHold();
			convertView = inflater.inflate(R.layout.inflate_speed, null);
			viewhold.check =  convertView
					.findViewById(R.id.checkBox_inspeep1);
			// viewhold.mearry = (TextView) convertView
			// .findViewById(R.id.textm_smearry1);
			viewhold.image = (ImageView) convertView
					.findViewById(R.id.imageView_inspeed1);
			viewhold.phonename = (TextView) convertView
					.findViewById(R.id.textView_sphonename);
			viewhold.packgename = (TextView) convertView
					.findViewById(R.id.textView_spackage);
			convertView.setTag(viewhold);

		} else {
			viewhold = (ViewHold) convertView.getTag();
		}
		// viewhold.mearry.setText(list.get(position).getmearry());
		viewhold.image.setImageDrawable(list.get(position).getIcon());
		viewhold.phonename.setText(list.get(position).getphonename());
		viewhold.packgename.setText(list.get(position).getPackagename());

		viewhold.check
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						list.get(position).setSCheck(isChecked);

					}
				});
		viewhold.check.setChecked(list.get(position).isCheck());
		return convertView;
	}

	class ViewHold {
		// public TextView mearry;
		public TextView phonename;
		public TextView packgename;
		public CheckBox check;
		public ImageView image;

	}
}
