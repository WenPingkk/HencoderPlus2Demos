package com.sean.layout_10.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.sean.layout_10.Utils;

/**
 * Author WenPing
 * CreateTime 2019/5/5.
 * Description: 重写 onlayout,layout
 */
public class OneHundredView extends View {

    public OneHundredView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension((int) Utils.dpToPixel(100),(int) Utils.dpToPixel(100));
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l+100, t+100, r, b);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }
}
