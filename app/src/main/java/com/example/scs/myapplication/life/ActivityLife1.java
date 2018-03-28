package com.example.scs.myapplication.life;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.scs.myapplication.R;
import com.example.scs.myapplication.databinding.ActivityLife1Binding;

public class ActivityLife1 extends AppCompatActivity {
    ActivityLife1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_life1);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_life1);
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLife1.this, ActivityLife2.class);
                startActivity(intent);
            }
        });
        Log.i("life", "A onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("life", "A onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("life", "A onRsume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("life", "A onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("life", "A onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("life", "A onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("life", "A onDestroy");
    }

    public void close(View view) {
    }

    public void open(View view) {
    }
}
