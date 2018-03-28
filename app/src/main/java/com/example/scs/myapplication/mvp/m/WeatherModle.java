package com.example.scs.myapplication.mvp.m;

import com.example.scs.myapplication.mvp.base.BaseModle;

/**
 * Created by scs on 17-9-21.
 */

public class WeatherModle implements BaseModle {
    public void GetWeatherHttp(String cityname, final OKHttpTypeListener httpTypeListener) {
//        OkHttpUtils.get().url("http://www.baidu.com").build().buildCall(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//        httpTypeListener.Error(e.getMessage());
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
                httpTypeListener.Success("success");
//            }
//        });

    }
}
