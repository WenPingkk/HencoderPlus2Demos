package com.sean.animation_08.views;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.sean.animation_08.Utils;

/**
 * Author WenPing
 * CreateTime 2019/5/2.
 * Description:
 */
public class CameraView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Camera camera = new Camera();

    float topFlip = 0;
    float bottomFlip = 0;
    float flipRotation = 0;

    public CameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        camera.setLocation(0,0, Utils.getZForCamera());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        //平移一次
        canvas.translate(100+600/2,100+600/2);
        canvas.rotate(-flipRotation);//画布旋转-20度
        camera.save();
        camera.rotateX(topFlip);
        camera.applyToCanvas(canvas);
        camera.restore();
        //画布裁剪
        canvas.clipRect(-600, -600, 600, 0);
        //平移回去
        canvas.rotate(flipRotation);
        canvas.translate(-(100+600/2),-(100+600/2));
        canvas.drawBitmap(Utils.getAvatar(getResources(),600),
                100,
                100,
                paint);
        canvas.restore();

        //下半部分
        canvas.save();
        canvas.translate(100+600/2,100+600/2);
        canvas.rotate(-flipRotation);
        camera.save();
        camera.rotateX(bottomFlip);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.clipRect(-600, 0, 600, 600);
        canvas.rotate(flipRotation);
        canvas.translate(-(100+600/2),-(100+600/2));
        canvas.drawBitmap(Utils.getAvatar(getResources(),
                600),
                100,
                100,
                paint);
        canvas.restore();
    }
    public float getBottomFlip() {
        return bottomFlip;
    }

    public void setBottomFlip(float bottomFlip) {
        this.bottomFlip = bottomFlip;
        invalidate();
    }

    public float getFlipRotation() {
        return flipRotation;
    }

    public void setFlipRotation(float flipRotation) {
        this.flipRotation = flipRotation;
        invalidate();
    }

    public float getTopFlip() {
        return topFlip;
    }

    public void setTopFlip(float topFlip) {
        this.topFlip = topFlip;
        invalidate();
    }
}
