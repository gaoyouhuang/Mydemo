package com.example.scs.myapplication.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.scs.myapplication.R;


/**
 * Created by scs on 18-2-26.
 */

public class CustomLayout extends ViewGroup {

    private static final String TAG = "CustomLayout";

    public CustomLayout(Context context) {
        this(context, null);
    }

    public CustomLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        measureChildren(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
//                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        int layoutWidth = 0;
        int layoutHeight = 0;
        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int count = getChildCount();
        int maxHeight = 0;
        int maxWidth = 0;

        if (modeWidth == MeasureSpec.EXACTLY) {
            layoutWidth = sizeWidth;
        } else {
            for (int i = 0; i < count; i++) {
                maxWidth = getChildAt(i).getWidth() > maxWidth ? getChildAt(i).getWidth() : maxWidth;
            }
            layoutWidth = maxWidth;
        }

        if (modeHeight == MeasureSpec.EXACTLY) {
            layoutHeight = sizeHeight;
        } else {
            for (int i = 0; i < count; i++) {
                maxHeight = getChildAt(i).getHeight() > maxHeight ? getChildAt(i).getHeight() : maxHeight;
            }
            layoutHeight = maxHeight;
        }
        setMeasuredDimension(layoutWidth, layoutHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int childMeasureWidth = 0;
        int childMeasuerHeight = 0;
        int layoutWidth = 0;
        int layoutHeight = 0;
        int lineMaxHeight = 0;

        CustomLayoutParams params = null;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            childMeasuerHeight = child.getMeasuredHeight();
            childMeasureWidth = child.getMeasuredWidth();

            params = (CustomLayoutParams) child.getLayoutParams();
            switch (params.position) {
                case CustomLayoutParams.POSITION_MIDDLE:    // 中间
                    l = (getWidth() - childMeasureWidth) / 2;
                    t = (getHeight() - childMeasuerHeight) / 2;
                    break;
                case CustomLayoutParams.POSITION_LEFT:      // 左上方
                    l = 0;
                    t = 0;
                    break;
                case CustomLayoutParams.POSITION_RIGHT:     // 右上方
                    l = getWidth() - childMeasureWidth;
                    t = 0;
                    break;
                case CustomLayoutParams.POSITION_BOTTOM:    // 左下角
                    l = 0;
                    t = getHeight() - childMeasuerHeight;
                    break;
                case CustomLayoutParams.POSITION_RIGHTANDBOTTOM:// 右下角
                    l = getWidth() - childMeasureWidth;
                    t = getHeight() - childMeasuerHeight;
                    break;
                default:
                    break;
            }
            child.layout(l, t, l + childMeasureWidth, t + childMeasuerHeight);
        }


//        for (int i = 0; i < count; i++) {
//            View child = getChildAt(i);
//            childMeasuerHeight = child.getMeasuredHeight();
//            childMeasureWidth = child.getMeasuredWidth();
//            if ((layoutWidth + childMeasureWidth) < getWidth()) {
//                l = layoutWidth;
//                t = layoutHeight;
//                r = l + childMeasureWidth;
//                b = t + childMeasuerHeight;
//            } else {
//                layoutWidth = 0;
//                layoutHeight = lineMaxHeight + layoutHeight;
//                lineMaxHeight = 0;
//                l = layoutWidth;
//                t = layoutHeight;
//                r = l + childMeasureWidth;
//                b = t + childMeasuerHeight;
//            }
//            layoutWidth = layoutWidth + childMeasureWidth;
//            lineMaxHeight = lineMaxHeight < childMeasuerHeight ? childMeasuerHeight : lineMaxHeight;
//            child.layout(l, t, r, b);
//        }
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new CustomLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new CustomLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new CustomLayoutParams(p);
    }

    public static class CustomLayoutParams extends MarginLayoutParams {
        public static final int POSITION_MIDDLE = 0; // 中间
        public static final int POSITION_LEFT = 1; // 左上方
        public static final int POSITION_RIGHT = 2; // 右上方
        public static final int POSITION_BOTTOM = 3; // 左下角
        public static final int POSITION_RIGHTANDBOTTOM = 4; // 右下角

        public int position = POSITION_LEFT;  // 默认我们的位置就是左上角

        public CustomLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.CustomLayout);
            //获取设置在子控件上的位置属性
            position = a.getInt(R.styleable.CustomLayout_layout_position, position);

            a.recycle();
        }

        public CustomLayoutParams(int width, int height) {
            super(width, height);
        }

        public CustomLayoutParams(LayoutParams source) {
            super(source);
        }

    }
}
