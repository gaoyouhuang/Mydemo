package com.example.scs.myapplication.ui;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.scs.myapplication.R;

/**
 * Created by scs on 17-12-4.
 */

public class PopupLightBelt extends PopupWindow {
    Context context;

    public PopupLightBelt(Context context) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.popup_light_belt, null);//子类
        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setFocusable(true);
        this.setOutsideTouchable(false);
        this.setAnimationStyle(R.style.myPopupWindow);
        View viewP = LayoutInflater.from(context).inflate(R.layout.activity_main, null);//父类
        this.showAtLocation(viewP, Gravity.CENTER, 0, 0);//相对于父控件
        init(view);
    }

    private void init(View view) {
        view.findViewById(R.id.rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        ColorPickView colorPickView = (ColorPickView) view.findViewById(R.id.color_picker_view);
        colorPickView.setOnColorChangedListener(new ColorPickView.OnColorChangedListener() {
            @Override
            public void onColorChange(int color) {
                Toast.makeText(context, color + "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
