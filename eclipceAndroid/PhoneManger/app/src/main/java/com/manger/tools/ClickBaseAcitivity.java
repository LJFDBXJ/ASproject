package com.manger.tools;

import android.view.View;
import android.view.View.OnClickListener;
import com.manger.R;
import com.manger.activity.AboutActivity;
import com.manger.activity.SetActivity;
import com.manger.base_ativity.BaseActivity;

public class ClickBaseAcitivity extends BaseActivity {

	public void MyOnClickListener() {

		Actionbar bar = (Actionbar) findViewById(R.id.action_bar);
		String title = getResources().getString(R.string.home_title);
		bar.initActionBar(title, R.drawable.ic_laun,
				R.drawable.ic_child_configs, new OnClickListener() {

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

}
