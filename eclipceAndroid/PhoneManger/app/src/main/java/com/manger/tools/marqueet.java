package com.manger.tools;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class marqueet extends TextView {

	public marqueet(Context context) {
		super(context);

	}

	public marqueet(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

	}

	public marqueet(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	@Override
	public boolean isFocused() {
		return true;
	}

}
