package com.example.scs.myapplication.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.scs.myapplication.R;

public class MdZFBActivity extends AppCompatActivity {
    Toolbar toolbar;
    AppBarLayout appbarlayout;
    TextView tv_1,tv_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md_zfb);

        tv_1 = (TextView) findViewById(R.id.tv_1);
        tv_2 = (TextView) findViewById(R.id.tv_2);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        appbarlayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        appbarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                System.out.println("verticalOffset = [" + verticalOffset + "]" + "{" + Math.abs(verticalOffset) + "}" + "{:" + appBarLayout.getTotalScrollRange() + "}");
                if (verticalOffset == 0) {
                    //完全展开
                    tv_1.setVisibility(View.VISIBLE);
                    tv_2.setVisibility(View.GONE);
//                    setToolbar1Alpha(255);
                } else if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                    //appBarLayout.getTotalScrollRange() == 200
                    //完全折叠
                    tv_1.setVisibility(View.GONE);
                    tv_2.setVisibility(View.VISIBLE);
//                    setToolbar2Alpha(255);
                } else {//0~200上滑下滑
                    if (tv_1.getVisibility() == View.VISIBLE) {
//                        //操作Toolbar1
                        int alpha = 300 - 155 - Math.abs(verticalOffset);
                        Log.i("alpha:", alpha + "");
//                        setToolbar1Alpha(alpha);

                    } else if (tv_2.getVisibility() == View.VISIBLE) {
                        if (Math.abs(verticalOffset) > 0 && Math.abs(verticalOffset) < 200) {
                            tv_1.setVisibility(View.VISIBLE);
                            tv_2.setVisibility(View.GONE);
//                            setToolbar1Alpha(255);
                        }
//                        //操作Toolbar2
                        int alpha = (int) (255 * (Math.abs(verticalOffset) / 100f));
//                        setToolbar2Alpha(alpha);
                    }
                }
            }
        });
    }
}
