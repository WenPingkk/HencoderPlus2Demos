package com.sean.hencoderplusdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.sean.hencoderplusdemo.Utils;

/**
 * Author WenPing
 * CreateTime 2019/5/2.
 * Description:
 */
public class PieChart extends View {

    /**
     * 常量
     */
    private static final int RADIUS = (int) Utils.dp2px(150);
    private static final int LENGTH = (int) Utils.dp2px(20);
    private static final int PULLED_OUT_INDEX = 2;

    /**
     * configuration
     */
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF bounds = new RectF();
    int[] angles = new int[]{60, 120, 90, 90};
    int[] colors = new int[]{
            Color.parseColor("#2979FF"),
            Color.parseColor("#C2185B"),
            Color.parseColor("#009688"),
            Color.parseColor("#FF8F00")};

    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        /**
         * 尺寸大小
         */
        bounds.set(
                getWidth() / 2 - RADIUS,
                getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS,
                getHeight() / 2 + RADIUS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int currentAngle = 0;
        for (int i = 0; i < colors.length; i++) {
            paint.setColor(colors[i]);
            /**
             * 针对特定index的 部分 进行平移.这里需要用的是:先保存canvas,然后是移动和绘制,最后要restore操作
             */
            canvas.save();
            if (i == PULLED_OUT_INDEX) {
                canvas.translate(
                        (float) Math.cos(Math.toRadians(currentAngle + angles[i] / 2)) * LENGTH,
                        (float) Math.sin(Math.toRadians(currentAngle + angles[i] / 2)) * LENGTH);
            }
            canvas.drawArc(
                    bounds,
                    currentAngle,
                    angles[i],
                    true,
                    paint);
            canvas.restore();
            currentAngle += angles[i];
        }
    }
}
