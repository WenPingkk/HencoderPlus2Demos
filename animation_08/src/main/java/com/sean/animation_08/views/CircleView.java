package com.sean.animation_08.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author WenPing
 * CreateTime 2019/5/4.
 * Description:
 */
public class CircleView extends View{

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        invalidate();
    }

    private float radius;

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    {
        paint.setColor(Color.RED);
    }
    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth()/2,getHeight()/2,radius,paint);
    }
}
