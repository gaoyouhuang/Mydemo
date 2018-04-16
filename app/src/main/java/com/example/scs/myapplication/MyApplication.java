package com.example.scs.myapplication;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import retrofit2.Retrofit;

/**
 * Created by scs on 17-9-21.
 */

public class MyApplication extends MultiDexApplication {
    private Retrofit retrofit;

    public static Context instance;

    public static Context getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = getApplicationContext();
    }



}