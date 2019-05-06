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
public class ScalableImageView extends View {

    Bitmap mBitmap;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private static final float IMAGE_WIDTH = Utils.dpToPixel(300);
    private static final float OVER_SCALE_FACTOR = 1.5f;
    private float originalOffsetX;
    private float originalOffsetY;

    private float currentScale;
    private float smallScale;
    private float bigScale;
    private boolean big;
    float offsetX;
    float offsetY;
    private GestureListener listener = new GestureListener();
    private ScaleGestureListener mScaleGestureListener = new ScaleGestureListener();

    private GestureDetectorCompat mGestureDetector;
    private ScaleGestureDetector mScaleGestureDetector;
    private OverScroller mScroller;

    public ScalableImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mBitmap = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);
        mGestureDetector = new GestureDetectorCompat(getContext(), listener);
        mScroller = new OverScroller(getContext());
        mScaleGestureDetector = new ScaleGestureDetector(getContext(), mScaleGestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = mScaleGestureDetector.onTouchEvent(event);
        //当前没有缩放操作
        if (!mScaleGestureDetector.isInProgress()) {
            result = mGestureDetector.onTouchEvent(event);
        }
        return result;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        originalOffsetX = (getWidth() - mBitmap.getWidth()) / 2;
        originalOffsetY = (getHeight() - mBitmap.getHeight()) / 2;

        //获得最小,最大缩放比
        if ((float) mBitmap.getWidth() / mBitmap.getHeight() > (float) getWidth() / getHeight()) {
            smallScale = (float) getWidth() / mBitmap.getWidth();
            bigScale = (float) getHeight() / mBitmap.getHeight() * OVER_SCALE_FACTOR;
        } else {
            smallScale = (float) getHeight() / mBitmap.getHeight();
            bigScale = (float) getWidth() / mBitmap.getWidth() * OVER_SCALE_FACTOR;
        }
        currentScale = smallScale;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float scaleFraction = (currentScale - smallScale) / (bigScale - smallScale);
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction);
        canvas.scale(currentScale, currentScale, getWidth() / 2f, getHeight() / 2f);
        canvas.drawBitmap(mBitmap, originalOffsetX, originalOffsetY, mPaint);
    }

    public float getCurrentScale() {
        return currentScale;
    }

    public void setCurrentScale(float currentScale) {
        this.currentScale = currentScale;
        invalidate();
    }

    private ObjectAnimator mScaleAnimator;

    //动画
    private ObjectAnimator getScaleAnimator() {
        if (mScaleAnimator == null) {
            mScaleAnimator = ObjectAnimator.ofFloat(this, "currentScale", 0);
        }
        mScaleAnimator.setFloatValues(smallScale, bigScale);
        return mScaleAnimator;
    }

    /**
     *
     */
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

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            big = !big;
            if (big) {
                offsetX = (e.getX() - getWidth() / 2f) - (e.getX() - getWidth() / 2) * bigScale / smallScale;
                offsetY = (e.getY() - getHeight() / 2f) - (e.getY() - getHeight() / 2) * bigScale / smallScale;
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
                offsetX -= distanceX;
                offsetY -= distanceY;
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
                mScroller.fling(
                        (int) offsetX,
                        (int) offsetY,
                        (int) velocityX,
                        (int) velocityX,
                        -(int) (mBitmap.getWidth() * bigScale - getWidth()) / 2,
                        (int) (mBitmap.getWidth() * bigScale - getWidth()) / 2,
                        -(int) (mBitmap.getHeight() * bigScale - getHeight()) / 2,
                        (int) (mBitmap.getHeight() * bigScale - getHeight()) / 2);
                postOnAnimation(mFlingRunner);
            }
            return false;
        }

        private void fixOffsets() {
            offsetX = Math.min(offsetX, (mBitmap.getWidth() * bigScale - getWidth()) / 2);
            offsetX = Math.max(offsetX, -(mBitmap.getWidth() * bigScale - getWidth()) / 2);

            offsetY = Math.min(offsetY, (mBitmap.getHeight() * bigScale - getHeight()) / 2);
            offsetY = Math.max(offsetY, -(mBitmap.getHeight() * bigScale - getHeight()) / 2);
        }
    }

    /**
     *
     */
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

    private FlingRunner mFlingRunner = new FlingRunner();

    class FlingRunner implements Runnable {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void run() {
            if (mScroller.computeScrollOffset()) {
                offsetX = mScroller.getCurrX();
                offsetY = mScroller.getCurrY();
                invalidate();
                postOnAnimation(this);
            }
        }
    }

}
