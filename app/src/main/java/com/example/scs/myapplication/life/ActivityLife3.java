package com.example.scs.myapplication.life;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.scs.myapplication.R;

public class ActivityLife3 extends Activity {

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFinishOnTouchOutside(true);
        setContentView(R.layout.activity_life3);
        Log.i("life", "C onCreate");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("life", "C onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("life", "C onRsume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("life", "C onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("life", "C onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("life", "C onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("life", "C onDestroy");
    }

    public void btn(View view) {
        Intent intent = new Intent(ActivityLife3.this, ActivityLife1.class);
        startActivity(intent);
        finish();
    }
}
