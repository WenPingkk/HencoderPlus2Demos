package com.sean.touch_11.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Author WenPing
 * CreateTime 2019/5/6.
 * Description:
 */
public class TestLayout extends LinearLayout {
    private static final String TAG = "mTag";

    public TestLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //如果子view进行处理,则不走这里,子view不处理,则会走这里,并向上传递.
        Log.e(TAG, TestLayout.class.getSimpleName()+":onTouchEvent:"+event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG, TestLayout.class.getSimpleName()+":onInterceptTouchEvent:"+ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, TestLayout.class.getSimpleName()+":dispatchTouchEvent:"+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }
}
