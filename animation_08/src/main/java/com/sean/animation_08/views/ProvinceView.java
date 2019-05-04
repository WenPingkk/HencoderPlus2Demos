package com.sean.animation_08.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.sean.animation_08.Utils;

/**
 * Author WenPing
 * CreateTime 2019/5/4.
 * Description:
 */
public class ProvinceView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    String province = "北京市";

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
        invalidate();
    }

    {
        //硬件加速
        setLayerType(LAYER_TYPE_HARDWARE,null);

        paint.setTextSize(Utils.dpToPixel(50));
        paint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(province,getWidth()/2,getHeight()/2,paint);
    }

    public ProvinceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
