package com.example.scs.myapplication.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.scs.myapplication.R;
import com.example.scs.myapplication.bean.StudentBean;
import com.example.scs.myapplication.databinding.ActivityMain3Binding;
import com.example.scs.myapplication.life.ActivityLife1;
import com.example.scs.myapplication.mvp.Main4Activity;
import com.example.scs.myapplication.myview.Main5Activity;

import java.lang.ref.WeakReference;

/**
 * 自定义 双向绑定
 * handler 内存泄露
 */

public class Main3Activity extends AppCompatActivity {
    ActivityMain3Binding binding;
    StudentBean bean;
    private MyHandler myHandler;
    Main3Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main3);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main3);
        binding.setMain(this);
        bean = new StudentBean();
//        bean.setName("name");
//        binding.setData(bean);

        myHandler = new MyHandler(this);
    }

    public void onMainClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_1:
                if (!TextUtils.isEmpty(binding.myEt.getText().toString())) {
                    binding.myEt.setName(binding.myEt.getText().toString());
//                    Toast.makeText(view.getContext(), bean.getName() + "", Toast.LENGTH_SHORT).show();
                    Toast.makeText(view.getContext(), binding.myEt.getName() + "", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_2:
                bean.setName("修改Bean");
//                handler.sendEmptyMessage(1);
                myHandler.sendEmptyMessage(1);
                break;
            case R.id.btn_3:
                intent = new Intent(Main3Activity.this, Main4Activity.class);
                startActivity(intent);
                break;
            case R.id.btn_4:
                intent = new Intent(Main3Activity.this, Main5Activity.class);
                startActivity(intent);
                break;
            case R.id.btn_5:
                intent = new Intent(Main3Activity.this, ActivityLife1.class);
                startActivity(intent);
                break;
        }
    }

    //内存泄露 1
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(Main3Activity.this, "内存泄露1", Toast.LENGTH_SHORT).show();
        }
    };

    //对应解决方案 1
    private static class MyHandler extends Handler {
        WeakReference<Main3Activity> weakReference;

        public MyHandler(Main3Activity activity) {
            weakReference = new WeakReference<Main3Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (weakReference.get() != null) {
                weakReference.get().myhandler_way(weakReference.get());
            }
        }
    }

    public void myhandler_way(Main3Activity activity) {
        Toast.makeText(activity, "解决内存泄露1", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeCallbacksAndMessages(null);
    }


    //内存泄露 2   单例模式的是 尽量使用context.getApplicationContext

    //内存泄露 3   资源使用完后，要去注销     Timer 广播 bitmap等

    //非静态内部类（包括匿名内部类）默认就会持有外部类的引用，当非静态内部类对象的生命周期比外部类对象的生命周期长时，就会导致内存泄露。

    /**
     * 关键词1 非静态内部类
     * MyThread1  （非静态内部类）     MyThread （静态内部类）
     * 关键词2 非静态内部类，默认持有外部类的引用  如下所示，因为不知道内部类的生命周期，故会导致内存泄露。
     */

//    private static class MyThread extends Thread {
//        WeakReference<Main3Activity> weakReference;
//
//        public MyThread(Main3Activity activity) {
//            weakReference = new WeakReference<Main3Activity>(activity);
//        }
//
//        @Override
//        public void run() {
//            super.run();
//            if (weakReference.get() != null) {
//                weakReference.get().myhandler_way(weakReference.get());
//            }
//        }
//    }
//
//    private class MyThread1 extends Thread {
//        @Override
//        public void run() {
//            super.run();
//            myhandler_way(activity);
//        }
//    }

}
