package com.sean.drawable_09.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author WenPing
 * CreateTime 2019/5/4.
 * Description:
 */
public class DrawableView extends View {

    private Drawable drawable;

    {
        drawable = new MeshDrawable();
    }
    public DrawableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 这里是drawable 和canvas不同的用法
         */
        drawable.setBounds(100, 100, getWidth(), getHeight());
        drawable.draw(canvas);
    }
}
