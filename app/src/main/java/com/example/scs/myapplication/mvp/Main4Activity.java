package com.example.scs.myapplication.mvp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.scs.myapplication.R;
import com.example.scs.myapplication.mvp.base.AActivity;
import com.example.scs.myapplication.mvp.p.WeatherPresenter;
import com.example.scs.myapplication.mvp.v.WeatherView;

public class Main4Activity extends AActivity<WeatherPresenter, WeatherView> implements WeatherView {

    private String cityname = "";
//    WeatherPresenter presenter;

    @Override
    protected WeatherPresenter createPresenter() {
        return new WeatherPresenter();
    }

    @Override
    public int setlayout() {
        return R.layout.activity_main4;
    }

    @Override
    public int getloaderid() {
        return Main4Activity.this.hashCode();
    }

    @Override
    public void init() {
        cityname = "温州";

//        presenter = new WeatherPresenter();
//        presenter.attach(Main4Activity.this);
//        presenter.getWeather(cityname);

    }

    /**
     * 因为系统已经把Activity Fragment和loader绑定在一起
     * 故不能在onCreate 方法中去调用presenter，因为onCreate方法中，是对loader的初始化，故直接调用presenter中的方法会爆空
     * 如同生命周期那样，onStart方法中，回去调用loader的dastart方法。
     */
    @Override
    protected void onStart() {
        super.onStart();
        presenter.getWeather(cityname);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    public void SetHttpWeather(String data) {
        Log.i("Main4", "data");

    }

    @Override
    public void ShowLoading() {
        showProgressDialog();
    }

    @Override
    public void CencleLoading() {
        dismissProgressDialog();

    }

    @Override
    public void SetHttpError(String error) {
        Log.i("Main4", "error");

    }

}
