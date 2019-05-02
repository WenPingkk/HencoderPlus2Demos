package com.sean.hencoderplusdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.sean.hencoderplusdemo.Utils;

/**
 * Author WenPing
 * CreateTime 2019/5/2.
 * Description:
 */
public class SportView extends View {

    /**
     * 常量
     */
    private static final float RING_WIDTH = Utils.dp2px(20);
    private static final float RADIUS = Utils.dp2px(150);
    private static final int CIRCLE_COLOR = Color.parseColor("#90A4AE");
    private static final int HIGHLIGHT_COLOR = Color.parseColor("#FF4081");

    /**
     * configuration
     */
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint.FontMetrics fontMetrics = new Paint.FontMetrics();

    public SportView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setTextSize(Utils.dp2px(100));
        paint.setTypeface(
                Typeface.createFromAsset(getContext().getAssets(),
                        "Quicksand-Regular.ttf"));
        paint.getFontMetrics(fontMetrics);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 画出圆环
         */
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(CIRCLE_COLOR);
        paint.setStrokeWidth(RING_WIDTH);
        canvas.drawCircle(
                getWidth()/2,
                getHeight()/2,
                RADIUS,
                paint);
        /**
         * 绘制进度条
         * 宽度给定了,drawArc和DrawCicle没有多少区别,就是角度.
         */
        paint.setColor(HIGHLIGHT_COLOR);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(
                getWidth()/2- RADIUS,
                getHeight()/2-RADIUS,
                getWidth()/2+RADIUS,
                getHeight()/2+RADIUS,
                -90,
                225,
                false,
                paint
        );

        paint.setTextSize(Utils.dp2px(100));
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);
//        Rect rect = new Rect();
//        paint.getTextBounds("abab", 0, "abab".length(), rect);
        float offset = (fontMetrics.ascent + fontMetrics.descent) / 2;
        canvas.drawText("abab",
                getWidth() / 2,
                getHeight() / 2 - offset,paint);
    }
}
