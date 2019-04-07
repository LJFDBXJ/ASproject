package com.manger.tools;

import com.manger.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Actionbar extends LinearLayout {

	ImageView image_left, image_right;
	TextView tv_center;

	public Actionbar(Context context, AttributeSet attrs) {
		super(context, attrs);

		inflate(context, R.layout.action_bar, this);
		image_left = findViewById(R.id.image_left);
		image_right =findViewById(R.id.image_right);
		tv_center =findViewById(R.id.text_center);
	}

	public void initActionBar(String title, int leftID, int rightID,
			OnClickListener listen) {
		tv_center.setText(title);
		if (leftID == -1) {
			image_left.setVisibility(View.INVISIBLE);

		} else {
			image_left.setImageResource(leftID);
			image_left.setOnClickListener(listen);
		}
		if (rightID == -1) {
			image_right.setVisibility(View.INVISIBLE);
		} else {
			image_right.setImageResource(rightID);
			image_right.setOnClickListener(listen);
		}

	}
}
