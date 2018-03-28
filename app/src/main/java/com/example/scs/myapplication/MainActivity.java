package com.example.scs.myapplication;

import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scs.myapplication.activity.Main2Activity;
import com.example.scs.myapplication.activity.RXTextActivity;
import com.example.scs.myapplication.activity.SqliteActivity;
import com.example.scs.myapplication.bean.StudentBean;
import com.example.scs.myapplication.databinding.ActivityMainBinding;
import com.example.scs.myapplication.databinding.ItemDemoBinding;
import com.example.scs.myapplication.myinterface.IClick;
import com.example.scs.myapplication.retrofit.RetrofitActivity;
import com.example.scs.myapplication.ui.PopupLightBelt;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private StudentBean demoBean;
    private MyCount count;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        DataBindingUtil.inflate(getLayoutInflater(),R.layout.activity_main,view,true);
        binding.setTv1data("tv1_data");//加载数据 way1
        binding.tv2.setText("tv2_data");//加载数据 way2

        demoBean = new StudentBean();
        demoBean.setName("0");

        binding.setStudentdata(demoBean);
        count = new MyCount(10000, 1000);

        binding.setMain(this);//绑定点击事件 用法1

        //绑定点击事件 用法2
        binding.setIclick(new IClick() {
            @Override
            public void onClick1(View view) {
                Toast.makeText(view.getContext(), "点击事件用法2", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClick2(View view) {
                binding.tv3.setText("预约单");
            }

            @Override
            public void onClick3(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }

            @Override
            public void onClick4(View view, String msg) {
                Toast.makeText(view.getContext(), msg + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClick5(View view) {
                Intent intent = new Intent(MainActivity.this, RXTextActivity.class);
                startActivity(intent);
            }

            @Override
            public void onClick6(View view) {
                Intent intent = new Intent(MainActivity.this, RetrofitActivity.class);
                startActivity(intent);
            }

            @Override
            public void onClick7(View view) {
                Intent intent = new Intent(MainActivity.this, SqliteActivity.class);
                startActivity(intent);
            }

        });

        binding.setOrdercode("5");

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + "     list_item");
        }
        binding.setData(list);
    }

    /**
     * Bean 用法1
     * 绑定点击时间 用法1
     */
    public void onBtnChange(View view) {
        PopupLightBelt popupLightBelt = new PopupLightBelt(this);

        count.start();
    }

    public class MyCount extends CountDownTimer {

        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            demoBean.setName("over");
        }

        @Override
        public void onTick(long millisUntilFinished) {
            demoBean.setName("" + millisUntilFinished);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (count != null) {
            count.cancel();
            count = null;
        }
    }


    @BindingAdapter("android:text")
    public static void setText(TextView view, CharSequence text) {
        final CharSequence oldText = view.getText();
        if (text == oldText || (text == null && oldText.length() == 0)) {
            return;
        }
        if (text instanceof Spanned) {
            if (text.equals(oldText)) {
                return; // No change in the spans, so don't set anything.
            }
        } else if (text.toString().equals(oldText.toString())) {
            return; // No content changes, so don't set anything.
        }
        view.setText(text);
    }

    @BindingAdapter("android:data")
    public static void setData(RecyclerView recyclerView, List<String> list_data) {
        Adapter adapter = new Adapter(recyclerView.getContext());
        adapter.setDatas(list_data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    }


    @BindingAdapter("mytext")
    public static void setMytext(TextView textView, String msg) {
        textView.setText(msg + "");
    }

    public static class Adapter extends BaseRecyclerAdapter<String, Adapter.AdapterView> {

        public Adapter(Context context) {
            super(context);
        }

        @Override
        public AdapterView onCreateHolder(ViewGroup parent, int viewType) {

            ItemDemoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_demo, parent, false);
            return new AdapterView(binding);
        }

        @Override
        public void onBind(AdapterView viewHolder, int realPosition, String data) {
            viewHolder.binding.setData(data);
            viewHolder.binding.tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupLightBelt popupLightBelt = new PopupLightBelt(context);
                }
            });
            viewHolder.binding.executePendingBindings();
        }

        public class AdapterView extends RecyclerView.ViewHolder {
            ItemDemoBinding binding;

            public AdapterView(ItemDemoBinding binding) {
                super(binding.getRoot());
                this.binding = binding;

            }

            public ItemDemoBinding getBinding() {
                return binding;
            }

        }
    }

}
