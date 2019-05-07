package com.sean.multitouch_13;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Author WenPing
 * CreateTime 2019/5/7.
 * Description:
 */
public class MultiTouchView2 extends View {

    private static final float IMAGE_WIDTH = Utils.dpToPixel(200);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmap;

    private float downX;
    private float downY;
    private float offsetX;
    private float offsetY;
    private float originalOffsetX;
    private float originalOffsetY;

    public MultiTouchView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);
    }

    /**
     * 两个以及两个以上点配合
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float sumX = 0;
        float sumY = 0;
        int pointerCount = event.getPointerCount();
        boolean isPointerUp = event.getActionMasked() == MotionEvent.ACTION_POINTER_UP;
        for (int i = 0; i < pointerCount; i++) {
            //如果当前有up操作,获得这个 操作的index
            if (!(isPointerUp && i == event.getActionIndex())) {
                sumX += event.getX(i);
                sumY += event.getY(i);
            }
        }
        //up操作,减去一个pointer
        if (isPointerUp) {
            pointerCount -= 1;
        }
        //获取焦点
        float focusX = sumX / pointerCount;
        float focusY = sumY / pointerCount;
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_POINTER_UP:
                downX = focusX;
                downY = focusY;
                originalOffsetX = offsetX;
                originalOffsetY = offsetY;
                break;
            case MotionEvent.ACTION_MOVE:
                offsetX = originalOffsetX + focusX - downX;
                offsetY = originalOffsetY + focusY - downY;
                invalidate();
                break;
        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint);
    }
}
