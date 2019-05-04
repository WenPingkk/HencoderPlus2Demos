package com.sean.drawable_09.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sean.drawable_09.Utils;

/**
 * Author WenPing
 * CreateTime 2019/5/4.
 * Description:
 */
public class MeshDrawable extends Drawable {

    private static final int INTERVAL = (int) Utils.dpToPixel(80);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    {
        paint.setColor(Color.RED);
        paint.setStrokeWidth(Utils.dpToPixel(2));
    }
    @Override
    public void draw(@NonNull Canvas canvas) {
        for (int i = 0; i < getBounds().right; i+=INTERVAL) {
            for (int j = 0; j < getBounds().bottom; j+=INTERVAL) {
                //画横线
                canvas.drawLine(getBounds().left, j, getBounds().right, j, paint);
                //画竖线
                canvas.drawLine(i, getBounds().top, i, getBounds().bottom, paint);
            }
        }
    }

    /**
     * 透明
     * @param alpha
     */
    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter filter) {
        paint.setColorFilter(filter);
    }

    /**
     * 不透明
     * @return
     */
    @Override
    public int getOpacity() {
        return paint.getAlpha() == 0 ? PixelFormat.TRANSPARENT :
                paint.getAlpha() == 0xff ? PixelFormat.OPAQUE : PixelFormat.TRANSLUCENT;
    }
}
