package com.sean.layout_10.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Author WenPing
 * CreateTime 2019/5/5.
 * Description:
 */
public class SquareImageView extends android.support.v7.widget.AppCompatImageView{
    public SquareImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int size = Math.max(measuredWidth, measuredHeight);
        setMeasuredDimension(size,size);
    }
}
