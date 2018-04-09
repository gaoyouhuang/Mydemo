package com.example.scs.myapplication.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.scs.myapplication.R;


//https://www.jianshu.com/p/932568ed31af  沉浸式状态栏颜色
//https://blog.csdn.net/u013933720/article/details/78191282  支付宝
//https://www.jianshu.com/p/72d45d1f7d55 Behavior
//https://blog.csdn.net/yanzhenjie1003/article/details/51938425 yanzhenjie博客

//layout_scrollFlags　　https://www.jianshu.com/p/7caa5f4f49bd
//scroll 可滑动
//exitUntilCollapsed
//enterAlways
//snap　

public class MdStyleActivity extends AppCompatActivity {
    Toolbar toolbar;
    FloatingActionButton floatingActionButton;
    CoordinatorLayout root;
    CollapsingToolbarLayout c_toolbar_layout;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    AppBarLayout appBarLayout;
    private BottomSheetBehavior mBottomSheetBehavior;
    BottomSheetDialog mBottomSheetDialog;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md_style);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ////添加Flag把状态栏设为可绘制模式
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏为透明
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        root = (CoordinatorLayout) findViewById(R.id.root);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_add);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        c_toolbar_layout = (CollapsingToolbarLayout) findViewById(R.id.c_toolbar_layout);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        mBottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.tab_layout));

        initData();

        createBottomSheetDialog();

    }

    private void createBottomSheetDialog() {
        mBottomSheetDialog = new BottomSheetDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_dialog, null, false);

        view.findViewById(R.id.img_last).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.cancel();
            }
        });
        mBottomSheetDialog.setContentView(view);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {

                            case R.id.navigation_item_book:
                                Snackbar.make(root,
                                        "Snackbar", Snackbar.LENGTH_LONG)
                                        .setAction("OK", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(MdStyleActivity.this, MdZFBActivity.class);
                                                startActivity(intent);
                                            }
                                        })
                                        .show();
                                break;
                            case R.id.navigation_item_example:
                                break;
                            case R.id.navigation_item_blog:
                                break;
                            case R.id.navigation_item_about:
                                break;
                        }
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    private void initData() {

        c_toolbar_layout.setExpandedTitleColor(Color.WHITE);
        c_toolbar_layout.setCollapsedTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });

        findViewById(R.id.fab_add1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBottomSheetDialog.isShowing()) {
                    mBottomSheetDialog.cancel();
                } else {
                    mBottomSheetDialog.show();
                }
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.setDrawerIndicatorEnabled(true);//点击toolbar的坐上角的按钮，实现侧边栏显示
        mDrawerToggle.syncState();

        setupDrawerContent(mNavigationView);

        mBottomSheetBehavior.setPeekHeight(0);
        mBottomSheetBehavior.setSkipCollapsed(false);
    }

}
