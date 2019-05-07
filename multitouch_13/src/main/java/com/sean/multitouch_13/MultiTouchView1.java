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
public class MultiTouchView1 extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final int IMAGE_WIDTH = (int) Utils.dpToPixel(300);
    private float downX;
    private float downY;
    private float offsetX;
    private float offsetY;
    private float originalOffsetX;
    private float originalOffsetY;
    private int trackingPointerId;//当前移动的手指的id;
    private Bitmap mBitmap;
    {
        mBitmap = Utils.getAvatar(getResources(),IMAGE_WIDTH);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //位置信息是由 offsetX,offSetY决定的.
        canvas.drawBitmap(mBitmap,offsetX,offsetY,mPaint);
    }

    public MultiTouchView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                trackingPointerId = event.getPointerId(0);//第一次和view接触,index 为0
                downX = event.getX();
                downY = event.getY();
                originalOffsetX = offsetX;
                originalOffsetY = offsetY;
                break;
            case MotionEvent.ACTION_MOVE:
                //获得当前在tracking的手指的index ->获得ta的轨迹
                int index = event.findPointerIndex(trackingPointerId);
                offsetX = originalOffsetX + event.getX(index) - downX;
                offsetY = originalOffsetY + event.getY(index) - downY;
                invalidate();
                break;
                //当有新的手指 参与到view操作
            case MotionEvent.ACTION_POINTER_DOWN:
                //获得新加入的手指index
                int actionIndex = event.getActionIndex();
                trackingPointerId = event.getPointerId(actionIndex);
                downX = event.getX(actionIndex);
                downY = event.getY(actionIndex);
                originalOffsetX = offsetX;
                originalOffsetY = offsetY;
                break;
                //有手指离开view操作,但是不是最后一个
            case MotionEvent.ACTION_POINTER_UP:
                actionIndex = event.getActionIndex();
                int pointerId = event.getPointerId(actionIndex);
                //如果离开view的正是 trackingId,需要重新指定pointId;
                if (pointerId == trackingPointerId) {
                int newIndex;
                    if (actionIndex == event.getPointerCount() - 1) {
                        newIndex = event.getPointerCount()-2;
                    } else {
                        newIndex = event.getPointerCount() - 1;
                    }
                    //重新指定trackingId,并且更新downX,downY,以及初始偏离距离
                    trackingPointerId = event.getPointerId(newIndex);
                    downX = event.getX(actionIndex);
                    downY = event.getY(actionIndex);
                    originalOffsetX = offsetX;
                    originalOffsetY = offsetY;
                }
                break;
        }
        return true;
    }
}
