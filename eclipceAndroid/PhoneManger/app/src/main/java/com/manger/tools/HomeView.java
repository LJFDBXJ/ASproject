package com.manger.tools;

import com.manger.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class HomeView extends View {
	public Paint paint0;
	private Paint paint1;

	public HomeView(Context context, AttributeSet attrs) {
		super(context, attrs);

		paint1 = new Paint();
		paint1.setColor(getResources().getColor(R.color.deepskyblue));// 已用
		paint1.setAntiAlias(true);// 去除锯齿
		paint1.setAlpha(90);

		paint0 = new Paint();
		paint0.setColor(getResources().getColor(R.color.white));// 已用
		paint0.setAntiAlias(true);// 去除锯齿
		paint0.setAlpha(90);

	}

	int b;

	public void go(final int num) {
		b = 0;
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (b < num) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					postInvalidate();
					b++;
				}

			}
		}).start();

	}

	private RectF rect0;

	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);
		canvas.drawArc(rect0, 0, 360, true, paint0);
		canvas.drawArc(rect0, 135, b, true, paint1);

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int width = MeasureSpec.getSize(widthMeasureSpec);
		int high = MeasureSpec.getSize(heightMeasureSpec);
		rect0 = new RectF(80, 170, width-80 , high-170);
		setMeasuredDimension(width, high);
	}
}
