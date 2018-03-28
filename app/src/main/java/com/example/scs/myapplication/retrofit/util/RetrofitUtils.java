package com.example.scs.myapplication.retrofit.util;

import com.example.scs.myapplication.retrofit.StringConverterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by scs on 18-3-12.
 */

public class RetrofitUtils {
    private static RetrofitUtils retrofitUtils;

    public Retrofit getRetrofit() {
        return retrofit;
    }

    private Retrofit retrofit;

    private RetrofitUtils() {
        if (retrofit == null) {
            OkHttpClient client = OkHttp3Utils.getOkHttpSingletonInstance();
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .setLenient()
                    .create();//使用 gson coverter，统一日期请求格式
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.sojson.com/open/")
                    .addConverterFactory(StringConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
        }
    }

    public static Retrofit getInstance() {

        if (retrofitUtils == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofitUtils == null) {
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils.getRetrofit();
    }
}
