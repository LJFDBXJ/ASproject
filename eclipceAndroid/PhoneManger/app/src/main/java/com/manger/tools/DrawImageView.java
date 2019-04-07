package com.manger.tools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class DrawImageView extends View {
    private final Paint paint;
    private final Context context;
    RectF rectf;

    public DrawImageView(Context context, AttributeSet attrs) {
        super( context, attrs );
        this.context = context;
        this.paint = new Paint();
        this.paint.setAntiAlias( true ); // 消除锯齿
        this.paint.setStyle( Style.STROKE );

        /**
         * this.paint.setStyle(Style.FILL_AND_STROKE)
         * this.paint.setStyle(Style.STROKE) 绘制空心圆或 空心矩形 ;
         * this.paint.setStyle(Style.FILL);//填充
         */
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw( canvas );

        int centerx = getWidth() / 2;
        int innerCircle = dip2px( context, 140 ); // 内圆半径
        int ringWidth = dip2px( context, 20 ); // 圆环宽度


        // 绘制内圆
        this.paint.setARGB( 90, 0, 248, 255 );
        this.paint.setStrokeWidth( ringWidth );
        canvas.drawCircle( getWidth() >> 1, getHeight() >> 1, innerCircle, this.paint );


        // 绘制圆hu
        this.paint.setARGB( 255, 0, 226, 187 );
        this.paint.setStrokeWidth( ringWidth );
        canvas.drawArc( rectf, 135, 270, true, this.paint );
//
        this.paint.setARGB( 90, 0, 0, 255 );
        this.paint.setStrokeWidth( ringWidth-10 );
        canvas.drawArc( rectf, 45, 90, true, this.paint );

    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素) DisplayMetrics展示度量density密度
     */
    public static int dip2px(Context context, float dpValue) {

        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure( widthMeasureSpec, heightMeasureSpec );

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int high = MeasureSpec.getSize(heightMeasureSpec);
        rectf = new RectF(60, 150, width-60 , high-150);
        setMeasuredDimension(width, high);
    }
}