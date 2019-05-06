package com.sean.scalable_12;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.OverScroller;

/**
 * Author WenPing
 * CreateTime 2019/5/6.
 * Description:
 */
public class ScalableView extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final float WIDTH_IMAGE = Utils.dpToPixel(300);
    private float OVER_SCALE_FACTOR = 1.5f;
    private float xOffset;
    private float yOffset;

    private float smallScale;
    private float maxScale;
    private float currentScale;
    private float originalOffsetX;
    private float originalOffsetY;

    private boolean big; //如果是放大效果

    private GestureDetectorCompat gestureDetector;
    private ScaleGestureDetector scaleGestureDetector;
    private Bitmap mBitmap;
    private OverScroller scroller;
    private FlingRunner mFlingRunner = new FlingRunner();
    private GestureListener mListener = new GestureListener();
    private ScaleGestureListener scaleListener = new ScaleGestureListener();

    public ScalableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        scroller = new OverScroller(context);
        mBitmap = Utils.getAvatar(getResources(), (int) WIDTH_IMAGE);
        gestureDetector = new GestureDetectorCompat(context, mListener);
        scaleGestureDetector = new ScaleGestureDetector(context, scaleListener);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        originalOffsetX = (getWidth() - mBitmap.getWidth()) / 2;
        originalOffsetY = (getHeight() - mBitmap.getHeight()) / 2;

        if ((float) mBitmap.getWidth() / mBitmap.getHeight() > (float) getWidth() / getHeight()) {
            smallScale = (float) getWidth() / mBitmap.getWidth();
            maxScale = (float) getHeight() / mBitmap.getHeight() * OVER_SCALE_FACTOR;
        } else {
            smallScale = (float) getHeight() / mBitmap.getHeight();
            maxScale = (float) getWidth() / mBitmap.getWidth() * OVER_SCALE_FACTOR;
        }
        currentScale = smallScale;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float scaleFraction = (currentScale - smallScale) / (maxScale - smallScale);
        canvas.translate(xOffset * scaleFraction, yOffset * scaleFraction);
        canvas.scale(currentScale, currentScale, getWidth() / 2f, getHeight() / 2f);
        canvas.drawBitmap(mBitmap, originalOffsetX, originalOffsetY, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = scaleGestureDetector.onTouchEvent(event);
        if (!scaleGestureDetector.isInProgress()) {
            result = gestureDetector.onTouchEvent(event);
        }
        return result;
    }

    public float getCurrentScale() {
        return currentScale;
    }

    public void setCurrentScale(float currentScale) {
        this.currentScale = currentScale;
        invalidate();
    }

    private ObjectAnimator scalAnimator;

    private ObjectAnimator getScaleAnimator() {
        if (scalAnimator == null) {
            scalAnimator = ObjectAnimator.ofFloat(this,
                    "currentScale", 0);
        }
        scalAnimator.setFloatValues(smallScale, maxScale);
        return scalAnimator;
    }

    class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        //双击效果
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            big = !big;
            if (big) {
                xOffset = (e.getX() - getWidth() / 2f) - (e.getX() - getWidth() / 2) * maxScale / smallScale;
                yOffset = (e.getY() - getHeight() / 2f) - (e.getY() - getHeight() / 2) * maxScale / smallScale;
                fixOffsets();
                getScaleAnimator().start();
            } else {
                getScaleAnimator().reverse();
            }
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (big) {
                xOffset -= distanceX;
                yOffset -= distanceY;
                fixOffsets();
                invalidate();
            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        //滑动
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (big) {
                scroller.fling(
                        (int) xOffset,
                        (int) yOffset,
                        (int) velocityX,
                        (int) velocityX,
                        -(int) (mBitmap.getWidth() * maxScale - getWidth()) / 2,
                        (int) (mBitmap.getWidth() * maxScale - getWidth()) / 2,
                        -(int) (mBitmap.getHeight() * maxScale - getHeight()) / 2,
                        (int) (mBitmap.getHeight() * maxScale - getHeight()) / 2);
                postOnAnimation(mFlingRunner);
            }
            return false;
        }

        private void fixOffsets() {
            xOffset = Math.min(xOffset, (mBitmap.getWidth() * maxScale - getWidth()) / 2);
            xOffset = Math.max(xOffset, - (mBitmap.getWidth() * maxScale - getWidth()) / 2);
            yOffset = Math.min(yOffset, (mBitmap.getHeight() * maxScale - getHeight()) / 2);
            yOffset = Math.max(yOffset, - (mBitmap.getHeight() * maxScale - getHeight()) / 2);
        }
    }


    class ScaleGestureListener implements ScaleGestureDetector.OnScaleGestureListener {

        float initialScale;

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            currentScale = initialScale * detector.getScaleFactor();
            invalidate();
            return false;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            initialScale = currentScale;
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {

        }
    }

    class FlingRunner implements Runnable {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void run() {
            if (scroller.computeScrollOffset()) {
                xOffset = scroller.getCurrX();
                yOffset = scroller.getCurrY();
                invalidate();
                postOnAnimation(this);
            }
        }
    }
}
