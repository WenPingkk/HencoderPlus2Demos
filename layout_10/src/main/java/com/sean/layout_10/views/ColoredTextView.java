package com.sean.layout_10.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.sean.layout_10.Utils;

import java.util.Random;

/**
 * Author WenPing
 * CreateTime 2019/5/5.
 * Description:
 */
public class ColoredTextView extends android.support.v7.widget.AppCompatTextView {

    private static final int[] COLORS = {
            Color.parseColor("#E91E63"),
            Color.parseColor("#673AB7"),
            Color.parseColor("#3F51B5"),
            Color.parseColor("#2196F3"),
            Color.parseColor("#009688"),
            Color.parseColor("#FF9800"),
            Color.parseColor("#FF5722"),
            Color.parseColor("#795548")
    };

    private static final int[] TEXT_SIZES = {
            16, 22, 28
    };
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private static int CORNER_RADIUS = (int) Utils.dpToPixel(4);
    private static final int X_PADDING = (int) Utils.dpToPixel(16);
    private static final int Y_PADDING = (int) Utils.dpToPixel(8);
    private Random random = new Random();
    public ColoredTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //静态代码块
    {
        setTextColor(Color.WHITE);
        setTextSize(TEXT_SIZES[random.nextInt(3)]);
        mPaint.setColor(COLORS[random.nextInt(COLORS.length)]);
        setPadding(X_PADDING, Y_PADDING, X_PADDING, Y_PADDING);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(0,0,getWidth(),getHeight(),CORNER_RADIUS,CORNER_RADIUS,mPaint);
        super.onDraw(canvas);
    }
}
