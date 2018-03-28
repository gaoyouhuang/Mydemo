package com.example.scs.myapplication.mvp.v;

import com.example.scs.myapplication.mvp.base.BaseView;

/**
 * Created by scs on 17-9-21.
 */

public interface WeatherView extends BaseView {

    void SetHttpWeather(String data);//http成功回调

    void SetHttpError(String error);//http 错误信息
}
