package com.sean.hencoderplusdemo.view;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.sean.hencoderplusdemo.Utils;

/**
 * Author WenPing
 * CreateTime 2019/5/2.
 * Description:
 */
public class CameraView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Camera camera = new Camera();

    public CameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        camera.rotateX(45);
        camera.setLocation(0,0, Utils.getZForCamera());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        //平移一次
        canvas.translate(100+600/2,100+600/2);
        canvas.rotate(-20);//画布旋转-20度
        //画布裁剪
        canvas.clipRect(-600, -600, 600, 0);
        //平移回去
        canvas.rotate(20);
        canvas.translate(-(100+600/2),-(100+600/2));
        canvas.drawBitmap(Utils.getAvatar(getResources(),600),
                100,
                100,
                paint);
        canvas.restore();

        //下半部分

        canvas.save();
        canvas.translate(100+600/2,100+600/2);
        canvas.rotate(-20);
        camera.applyToCanvas(canvas);
        canvas.clipRect(-600, 0, 600, 600);
        canvas.rotate(20);
        canvas.translate(-(100+600/2),-(100+600/2));
        canvas.drawBitmap(Utils.getAvatar(getResources(),
                600),
                100,
                100,
                paint);
        canvas.restore();

    }
}
