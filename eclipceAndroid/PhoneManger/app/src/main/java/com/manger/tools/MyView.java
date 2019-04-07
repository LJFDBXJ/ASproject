package com.manger.tools;

import com.manger.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
	public Paint paint;
	public Paint paint1;
	public Paint paint2;


	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		paint1 = new Paint();
		paint1.setColor(getResources().getColor(R.color.mediu));// 已用
		paint1.setAntiAlias(true);// 去除锯齿

		paint = new Paint();
		paint.setColor(getResources().getColor(R.color.dodger));// 总内存
		paint.setAntiAlias(true);

		paint2 = new Paint();
		paint2.setColor(getResources().getColor(R.color.deepskyblue));// 未用
		paint2.setAntiAlias(true);
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

	private RectF rect, rect1;

	@Override
	protected void onDraw(Canvas canvas) {
		rect = new RectF(45, 25, 540, 515);
		rect1 = new RectF(45, 25, 540, 515);
		super.onDraw(canvas);
		canvas.drawArc(rect1, 0, 360, true, paint2);
		canvas.drawArc(rect, 0, b, true, paint1);
		canvas.drawCircle(290, 270, 200, paint);

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int wid = MeasureSpec.getSize(widthMeasureSpec);
		int hei = MeasureSpec.getSize(heightMeasureSpec);

		setMeasuredDimension(wid, hei);
	}
}
