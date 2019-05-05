package com.sean.layout_10.views;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Author WenPing
 * CreateTime 2019/5/5.
 * Description:
 */
public class TagLayout extends ViewGroup {

    /**
     * 重点在于 1.list集合存储所有的childview的rect规范
     * 1.widthUsed
     * 2.LineWidthused
     * 3.heightUsed
     * 4,maxHeightUsed
     * 5
     */
    private ArrayList<Rect> childrenBounds = new ArrayList<>();

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthUsed = 0;
        int lineWidthUsed = 0;
        int heightUsed = 0;
        int maxHeightUsed = 0;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);

        //进行for循环.子view的吃饭和排布.
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            //针对specMode判断
            if (specMode != MeasureSpec.UNSPECIFIED && lineWidthUsed + child.getMeasuredWidth()> specWidth) {
                lineWidthUsed = 0;
                heightUsed += maxHeightUsed;
                maxHeightUsed = 0;
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            }
            Rect childBound;
            if (childrenBounds.size() <= i) {
                childBound = new Rect();
                childrenBounds.add(childBound);
            } else {
                childBound = childrenBounds.get(i);
            }
            childBound.set(lineWidthUsed, heightUsed, lineWidthUsed + child.getMeasuredWidth(), heightUsed + child.getMeasuredHeight());
            lineWidthUsed += child.getMeasuredWidth();
            widthUsed = Math.max(widthUsed, lineWidthUsed);
            maxHeightUsed = Math.max(child.getMeasuredHeight(), maxHeightUsed);
        }
        int width = widthUsed;
        int height = heightUsed +maxHeightUsed;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //循环布局
        for (int i = 0; i < childrenBounds.size(); i++) {
            View child = getChildAt(i);
            Rect rect = childrenBounds.get(i);
            child.layout(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
