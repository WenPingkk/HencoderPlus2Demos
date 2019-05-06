package com.sean.touch_11.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Author WenPing
 * CreateTime 2019/5/6.
 * Description:
 */
public class TestView extends View {

    private static final String TAG = "mTag";

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, TestView.class.getSimpleName() + ":onTouchEvent:"+event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i(TAG, TestView.class.getSimpleName() + ":dispatchTouchEvent:"+event.getAction());
        return super.dispatchTouchEvent(event);
    }
}
