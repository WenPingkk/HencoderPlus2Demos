package com.sean.scalable_12;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

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
    float offsetX;
    float offsetY;

    public ScalableImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mBitmap = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        originalOffsetX = (getWidth() - mBitmap.getWidth()) / 2;
        originalOffsetY = (getHeight() - mBitmap.getHeight()) / 2;

        //获得最小,最大缩放比
        if ((float) mBitmap.getWidth() / mBitmap.getHeight() > (float) getWidth() / getHeight()) {
            smallScale = (float) getWidth() / mBitmap.getWidth();
            bigScale= (float) getHeight() / mBitmap.getHeight() * OVER_SCALE_FACTOR;
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
        canvas.scale(currentScale,currentScale,getWidth()/2f,getHeight()/2f);
        canvas.drawBitmap(mBitmap,originalOffsetX,originalOffsetY,mPaint);
    }

    public float getCurrentScale() {
        return currentScale;
    }

    public void setCurrentScale(float currentScale) {
        this.currentScale = currentScale;
        invalidate();
    }
}
