package com.example.scs.myapplication.mvp.p;

import com.example.scs.myapplication.mvp.base.BaseModle;
import com.example.scs.myapplication.mvp.base.BasePresenter;
import com.example.scs.myapplication.mvp.m.WeatherModle;
import com.example.scs.myapplication.mvp.v.WeatherView;

/**
 * Created by scs on 17-9-21.
 */

public class WeatherPresenter extends BasePresenter<WeatherView> {

    private WeatherModle weatherModle;

    public WeatherPresenter() {
        weatherModle = new WeatherModle() {
        };
    }

    //获取天气接口
    public void getWeather(String cityname) {
        getView().ShowLoading();
        weatherModle.GetWeatherHttp(cityname, new BaseModle.OKHttpTypeListener() {
            @Override
            public void Success(String successdata) {
                getView().CencleLoading();
                getView().SetHttpWeather(successdata);
            }

            @Override
            public void Error(String errordata) {
                getView().CencleLoading();
                getView().SetHttpError(errordata);
            }
        });
    }
}
