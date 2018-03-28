package com.example.scs.myapplication.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.scs.myapplication.R;

import java.util.ArrayList;

/**
 * Created by scs on 18-2-24.
 */

public class MyTextView extends View {

    static final String TAG = "MyTextView";
    private String mText;
    private ArrayList<String> mTextList;

    private int mTextColor;

    private int mBackColor;

    private int mTextSize;

    private Rect mBound;
    private Paint paint;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTextList = new ArrayList<>();
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyTextView, defStyleAttr, 0);
        mText = a.getString(R.styleable.MyTextView_mText);
        mTextColor = a.getColor(R.styleable.MyTextView_mTextColor, Color.BLACK);
        mTextSize = (int) a.getDimension(R.styleable.MyTextView_mTextSize, 100);
        a.recycle();  //注意回收
        if (TextUtils.isEmpty(mText)) {
            mText = "MyTextView";
        }
        paint = new Paint();
        paint.setColor(mTextColor);
        paint.setTextSize(mTextSize);

        mBound = new Rect();
        paint.getTextBounds(mText, 0, mText.length(), mBound);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;
        int sumline = 0;//一共有几行

        int textLength = mBound.width();

        if (mTextList.size() == 0) {
            int padding = getPaddingLeft() + getPaddingRight();
            int showText = sizeWidth - padding;
            if (textLength <= showText) {
                //单行
                mTextList.add(mText);
            } else {
                double line = (double) textLength / (double) showText;
                if ((line + "").contains(".")) {
                    sumline = (int) (Math.floor(line) + 1);
                } else {
                    sumline = (int) (Math.floor(line));
                }

                int lineTextNum = (int) Math.ceil(mText.length() / sumline);//一行最多几个字符串 向下取整

                Log.i(TAG, "显示内容 " + mText);
                Log.i(TAG, "一共显示 " + sumline + " 行");
                Log.i(TAG, "一行显示 " + lineTextNum + " 个字符串");

                for (int i = 0; i < sumline; i++) {
                    String msg = "";
                    if (i == sumline - 1) {
                        msg = mText.substring(i * lineTextNum, mText.length());
                    } else {
                        msg = mText.substring(i * lineTextNum, (i + 1) * lineTextNum);
                    }
                    Log.i(TAG, "第 " + (i + 1) + " 行 " + "内容 " + msg);
                    mTextList.add(msg);
                }
            }
        }

        if (modeWidth == MeasureSpec.EXACTLY) {
            width = sizeWidth;
        } else {
            if (mTextList.size() == 1) {
                width = mBound.width() + getPaddingRight() + getPaddingLeft();
            } else {
                width = sizeWidth;
            }
        }

        if (modeHeight == MeasureSpec.EXACTLY) {
            height = sizeHeight;
        } else {
            if (mTextList.size() == 1) {
                height = mBound.height() + getPaddingBottom() + getPaddingTop();
            } else {
                height = mBound.height() * mTextList.size() + getPaddingBottom() + getPaddingTop();
            }
        }

        setMeasuredDimension(width, height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawText(mText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, paint);
        for (int i = 0; i < mTextList.size(); i++) {
            paint.getTextBounds(mTextList.get(i), 0, mTextList.get(i).length(), mBound);
            canvas.drawText(mTextList.get(i), (getWidth() / 2 - mBound.width() / 2), (getPaddingTop() + (mBound.height() * (i + 1))), paint);
        }

    }
}
