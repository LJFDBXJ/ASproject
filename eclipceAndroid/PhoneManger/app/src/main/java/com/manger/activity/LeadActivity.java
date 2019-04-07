package com.manger.activity;

import com.manger.R;
import com.manger.myadapater.MyPageAdapater;
import com.manger.tools.ClickBaseAcitivity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class LeadActivity extends ClickBaseAcitivity implements
		OnPageChangeListener {
	SharedPreferences prefer;// 使用SharedPreferences来记录程序使用的次数。
	ViewPager viewPager;
	MyPageAdapater pd;

	ImageView[] image = new ImageView[3];

	protected void onCreate(Bundle savedInstanceState) {
		prefer = getSharedPreferences("countt", 0);
		int count = prefer.getInt("count", 0);
		if (count ==0) {
			Editor editor = prefer.edit();
			// 把修改后的数据放到编辑器内。
			editor.putInt("count", ++count);
			// 提交修改后的数据。
			editor.commit();// commit()保存_key-value对


		}else{
			Intent intent = new Intent(LeadActivity.this, LogoActivity.class);
			startActivity(intent);
			finish();
		}



		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lead);
		viewPager =  findViewById(R.id.viewPager);

		LayoutInflater inflater = LayoutInflater.from(this);
		pd = new MyPageAdapater();
		View view1 = inflater.inflate(R.layout.inflate_viewpager, null);
		ImageView imag =  view1.findViewById(R.id.singelView1);
		imag.setImageResource(R.drawable.yin1);
		pd.add(imag);

		View view2 = inflater.inflate(R.layout.inflate_viewpager, null);
		ImageView imag2 =  view2.findViewById(R.id.singelView1);
		imag2.setImageResource(R.drawable.yin2);
		pd.add(view2);

		View view3 = inflater.inflate(R.layout.inflate_viewpager, null);
		ImageView imag3 = view3.findViewById(R.id.singelView1);
		imag3.setImageResource(R.drawable.yin3);
		pd.add(view3);
		viewPager.setAdapter(pd);



		image[0] =  findViewById(R.id.imageView1);
		image[1] =findViewById(R.id.imageView2);
		image[2] =  findViewById(R.id.imageView3);

		image[0].setImageResource(R.drawable.adware_style_selected);
		image[1].setImageResource(R.drawable.adware_style_default);
		image[2].setImageResource(R.drawable.adware_style_default);

		viewPager.setOnPageChangeListener(this);

	}

	public void onPageSelected(int arg0) {
		for (int i = 0; i < image.length; i++) {

			image[i].setImageResource(R.drawable.adware_style_default);

		}

		image[arg0].setImageResource(R.drawable.adware_style_selected);

		if (arg0 == 2) {
			Intent intent = new Intent(LeadActivity.this, LogoActivity.class);
			startActivity(intent);
			finish();
		}

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
	}

}
