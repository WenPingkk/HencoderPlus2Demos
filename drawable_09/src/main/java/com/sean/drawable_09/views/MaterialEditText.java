package com.sean.drawable_09.views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;

import com.sean.drawable_09.R;
import com.sean.drawable_09.Utils;

/**
 * Author WenPing
 * CreateTime 2019/5/4.
 * Description:MaterialEditText 功能实现
 */
public class MaterialEditText extends android.support.v7.widget.AppCompatEditText {

    private static final float TEXT_SIZE = Utils.dpToPixel(12);
    private static final float TEXT_MARGIN = Utils.dpToPixel(8);
    //竖直方向 偏离距离
    private static final int TEXT_VERTICAL_OFFSET = (int) Utils.dpToPixel(22);
    //水平方向的 偏离距离
    private static final int TEXT_HORIZONTAL_OFFSET = (int) Utils.dpToPixel(5);
    //动画 轨迹偏离距离
    private static final int TEXT_ANIMATION_OFFSET = (int) Utils.dpToPixel(16);
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private boolean useFloatingLabel;

    private float floatingLabelFraction;
    private boolean floatingLabelShown;
    private ObjectAnimator mAnimator;
    private Rect backgroundPadding = new Rect();

    private static final String TAG = "mTag";


    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText);
        useFloatingLabel = typedArray.getBoolean(R.styleable.MaterialEditText_useFloatingLabel, true);
        typedArray.recycle();

        mPaint.setTextSize(TEXT_SIZE);
        onUseFloatingLabelChanged();

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence sequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence sequence, int i, int i1, int i2) {
                if (useFloatingLabel) {
                    if (floatingLabelShown && TextUtils.isEmpty(sequence)) {
                        floatingLabelShown = false;
                        getAnimator().reverse();
                    } else if (!floatingLabelShown&& !TextUtils.isEmpty(sequence)){
                        floatingLabelShown = true;
                        getAnimator().start();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private ObjectAnimator getAnimator() {
        if (mAnimator == null) {
            mAnimator = ObjectAnimator.ofFloat(this, "floatingLabelFraction", 0, 1);
        }
        return mAnimator;

    }

    private void onUseFloatingLabelChanged() {
        //获取背景
        getBackground().getPadding(backgroundPadding);
        if (useFloatingLabel) {
            setPadding(getPaddingLeft(), (int) (backgroundPadding.top+TEXT_SIZE+TEXT_MARGIN),getPaddingRight(),getPaddingBottom());
            Log.e(TAG, "padding.top:" + (backgroundPadding.top + TEXT_MARGIN));
        } else {
            setPadding(getPaddingLeft(), backgroundPadding.top, getPaddingRight(), getPaddingBottom());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAlpha((int) (0xff*floatingLabelFraction));
        float extraOffset = TEXT_ANIMATION_OFFSET*(1-floatingLabelFraction);
        canvas.drawText(getHint().toString(),TEXT_HORIZONTAL_OFFSET,TEXT_VERTICAL_OFFSET+extraOffset,mPaint);
    }

    public float getFloatingLabelFraction() {
        return floatingLabelFraction;
    }

    public void setFloatingLabelFraction(float floatingLabelFraction) {
        this.floatingLabelFraction = floatingLabelFraction;
        invalidate();
    }
}
