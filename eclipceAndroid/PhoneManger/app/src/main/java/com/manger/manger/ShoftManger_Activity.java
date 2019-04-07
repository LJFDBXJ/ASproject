package com.manger.manger;

import com.manger.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ShoftManger_Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listviewshoft_manger_);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shoft_manger_, menu);
		return true;
	}

}
