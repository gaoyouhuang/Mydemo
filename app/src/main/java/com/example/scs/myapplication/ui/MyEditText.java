package com.example.scs.myapplication.ui;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.util.AttributeSet;

/**
 * Created by scs on 17-9-14.
 */
@InverseBindingMethods({
        @InverseBindingMethod(type = MyEditText.class, attribute = "android:name", event = "android:nameAttrChanged", method = "android:getName")
})

public class MyEditText extends android.support.v7.widget.AppCompatEditText {
    private static String name = "";


    @InverseBindingAdapter(attribute = "android:name", event = "android:nameAttrChanged")
    public static String getName(MyEditText editText) {
        name = editText.getText().toString();
        return editText.getText().toString();
    }

    @BindingAdapter(value = "android:name", requireAll = false)
    public static void setName(MyEditText editText, String setname) {
        if (setname == null)
            return;
        if (name.equals(setname))
            return;
        name = setname;
        editText.setText(setname + "");
    }

    @BindingAdapter(value = "android:nameAttrChanged")
    public static void setNameListenering(MyEditText editText, final InverseBindingListener listener) {
        if (listener != null) {
            editText.setOnNameListener(new OnNameListener() {
                @Override
                public void onName() {
                    listener.onChange();
                }
            });
        } else {
            editText.setOnNameListener(null);
        }
    }

    public void setName(String name) {
        if (name != null && !name.equals("")) {
            if (onNameListener != null) onNameListener.onName();
        }
    }

    public String getName() {
        return name;
    }

    public MyEditText(Context context) {
        super(context);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private OnNameListener onNameListener;

    public void setOnNameListener(OnNameListener onNameListener) {
        this.onNameListener = onNameListener;
    }

    public interface OnNameListener {
        void onName();
    }
}
