package com.example.scs.myapplication.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.scs.myapplication.R;
import com.example.scs.myapplication.bean.StudentBean;
import com.example.scs.myapplication.databinding.ActivityMain2Binding;

public class Main2Activity extends AppCompatActivity {
    ActivityMain2Binding binding;
    StudentBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2);

        bean = new StudentBean();
        bean.setName("双向绑定--Bean(非自定义)");

        binding.setChangedata(bean);

        binding.setMain(this);
    }


    public void main2click(View view) {
        switch (view.getId()) {
            case R.id.btn_1://双向绑定数据 需要 @={}
                if (!TextUtils.isEmpty(binding.etChange.getText().toString())) {
                    binding.tvChange.setText(binding.etChange.getText().toString());
                    Toast.makeText(view.getContext(), bean.getName(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(view.getContext(), "不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_2:
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(intent);
                break;
        }
    }


}
