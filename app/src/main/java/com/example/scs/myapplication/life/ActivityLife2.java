package com.example.scs.myapplication.life;

import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.scs.myapplication.R;
import com.example.scs.myapplication.databinding.ActivityLife2Binding;

public class ActivityLife2 extends AppCompatActivity {

    ActivityLife2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_life2);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_life2);
        binding.setLife2(this);
        Log.i("life", "B onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("life", "B onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("life", "B onRsume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("life", "B onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("life", "B onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("life", "B onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("life", "B onDestroy");
    }

    public void btnClick(View view) {
        finish();
    }

    public void btnClick1(View view) {
//        Dialog dialog = null;//非全屏dialog
//        AlertDialog.Builder alertBuilder = new
//                AlertDialog.Builder(ActivityLife2.this);
//        alertBuilder.setTitle("setTitle")
//                .setMessage("setMessage")
//                .setNegativeButton("Cancel",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                                finish();
//                            }
//                        })
//                .setPositiveButton("Confirm",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                                Intent intent = new Intent(ActivityLife2.this, ActivityLife3.class);
//                                startActivity(intent);
//                            }
//                        });
//        dialog = alertBuilder.create();
//        dialog.show();
        final Dialog dialog = new Dialog(this,R.style.Dialog_Fullscreen);  //全屏dialog
        LayoutInflater inflater = getLayoutInflater();
        View view1 = inflater.inflate(R.layout.dialog, null);

        TextView tv1 = (TextView) view1.findViewById(R.id.tv_1);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });
        TextView tv2 = (TextView) view1.findViewById(R.id.tv_2);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(ActivityLife2.this, ActivityLife3.class);
                startActivity(intent);
            }
        });
        dialog.setContentView(view1);
        dialog.show();
    }

}
