package com.example.scs.myapplication.myview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.scs.myapplication.R;
import com.example.scs.myapplication.lazyfragment.Fragment1;
import com.example.scs.myapplication.lazyfragment.Fragment2;
import com.example.scs.myapplication.lazyfragment.Fragment3;

import java.util.ArrayList;

public class Main5Activity extends AppCompatActivity {
    ViewPager viewPager;
    CommonViewPAdapter adapter;
    ArrayList<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        list = new ArrayList<>();
        list.add(new Fragment1());
        list.add(new Fragment2());
        list.add(new Fragment3());
        viewPager = (ViewPager) findViewById(R.id.vp);
        adapter = new CommonViewPAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
    }


    public class CommonViewPAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> arrayList;
//        String title[];

        public CommonViewPAdapter(FragmentManager fm, ArrayList<Fragment> arrayList) {
            super(fm);
            this.arrayList = arrayList;
//            this.title = title;
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

//        @Override
//        public CharSequence getPageTitle(int position) {
//            return title[position];
//        }

        @Override
        public Fragment getItem(int position) {
            return arrayList.get(position);
        }


    }
}
