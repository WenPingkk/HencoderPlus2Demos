package com.sean.layout_10.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.sean.layout_10.Utils;

/**
 * Author WenPing
 * CreateTime 2019/5/5.
 * Description:完全用自定义的尺寸来绘制.
 */
public class CircleView extends View {
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private static final float PADDING = Utils.dpToPixel(30);
    private static final float RADIUS = Utils.dpToPixel(80);

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float width = (PADDING + RADIUS) * 2;
        float height = (PADDING + RADIUS) * 2;
        setMeasuredDimension((int)width,(int)height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(PADDING+RADIUS,PADDING+RADIUS,RADIUS,mPaint);
    }
}
