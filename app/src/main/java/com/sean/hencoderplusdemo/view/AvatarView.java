package com.sean.hencoderplusdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.sean.hencoderplusdemo.R;
import com.sean.hencoderplusdemo.Utils;

/**
 * Author WenPing
 * CreateTime 2019/5/2.
 * Description:PorterDuffXfermode 用法
 */
public class AvatarView extends View {

    /**
     * 常量
     */

    private static final float WIDTH = Utils.dp2px(300);
    private static final float PADDING = Utils.dp2px(50);
    private static final float EDGE_WIDTH = Utils.dp2px(5);

    /**
     * configuration
     */
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    //
    Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    Bitmap bitmap;
    RectF savedArea = new RectF();

    {
        bitmap = getAvatar((int) WIDTH);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        savedArea.set(
                PADDING,
                PADDING,
                PADDING+WIDTH,
                PADDING+WIDTH);
    }

    public AvatarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawOval(
                PADDING,
                PADDING,
                PADDING+WIDTH,
                PADDING+WIDTH,
                paint);
        int saved = canvas.saveLayer(savedArea, paint);
        //这是更小的 圆形 衬托出边界
        canvas.drawOval(
                PADDING+EDGE_WIDTH,
                PADDING+EDGE_WIDTH,
                PADDING+WIDTH-EDGE_WIDTH,
                PADDING+WIDTH-EDGE_WIDTH,
                paint);
        paint.setXfermode(xfermode);
        canvas.drawBitmap(bitmap,PADDING,PADDING,paint);
        paint.setXfermode(null);
        canvas.restoreToCount(saved);


    }

    Bitmap getAvatar(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(
                getResources(),
                R.drawable.avatar_rengwuxian, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(
                getResources(),
                R.drawable.avatar_rengwuxian,
                options);
    }
}
