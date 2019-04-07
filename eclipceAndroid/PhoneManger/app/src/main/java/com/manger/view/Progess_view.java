package com.manger.view;

import java.util.Timer;
import java.util.TimerTask;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class Progess_view extends ProgressBar {
	public Progess_view(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	public void progess0(final int pro) {
		setProgress(0);
		final Timer teme = new Timer();

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				setProgress(getProgress() + 1);
				if (getProgress() > pro) {
					teme.cancel();
				}
				postInvalidate();
			}

		};
		teme.schedule(task, 40, 40);

	}
}
