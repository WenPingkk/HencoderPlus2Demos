package com.sean.multitouch_13;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_POINTER_DOWN;
import static android.view.MotionEvent.ACTION_POINTER_UP;
import static android.view.MotionEvent.ACTION_UP;

/**
 * Author WenPing
 * CreateTime 2019/5/7.
 * Description:
 */
public class MultiTouchView3 extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    //集合
    SparseArray<Path> paths = new SparseArray<>();


    public MultiTouchView3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.dpToPixel(4));
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case ACTION_DOWN:
            case ACTION_POINTER_DOWN:
                int actionIndex = event.getActionIndex();
                int pointerId = event.getPointerId(actionIndex);
                Path path = new Path();
                path.moveTo(event.getX(pointerId), event.getY(pointerId));//绘制路径
                paths.append(pointerId, path);//存储到集合
                invalidate();
                break;
            case ACTION_MOVE:
                for (int i = 0; i < event.getPointerCount(); i++) {
                    pointerId = event.getPointerId(i);
                    path = paths.get(pointerId);
                    path.lineTo(event.getX(i), event.getY(i));//遍历,并把所有的线进行绘制.
                }
                invalidate();
                break;
            case ACTION_UP:
            case ACTION_POINTER_UP:
                pointerId = event.getPointerId(event.getActionIndex());
                paths.remove(pointerId);//移除up操作的pointer的路径
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < paths.size(); i++) {
            Path path = paths.valueAt(i);
            canvas.drawPath(path,paint);
        }
    }
}
