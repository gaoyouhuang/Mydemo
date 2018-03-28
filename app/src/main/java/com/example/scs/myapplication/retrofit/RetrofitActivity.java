package com.example.scs.myapplication.retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.scs.myapplication.MyApplication;
import com.example.scs.myapplication.R;
import com.example.scs.myapplication.retrofit.util.RetrofitUtils;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity {

    private MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        myApplication = (MyApplication) getApplication();
    }

    public void retrofit(View view) {

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://www.sojson.com/open/")
//                .addConverterFactory(StringConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//        Request_Interface request_interface = retrofit.create(Request_Interface.class);
//
//        request_interface.getCity("北京")
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Consumer<Response<WeatherBean>>() {
//                    @Override
//                    public void accept(Response<WeatherBean> weatherBeanResponse) throws Exception {
//                        weatherBeanResponse.code();
//                    }
//                });

//        Call<WeatherBean> call = request_interface.getCity("北京");
//        call.enqueue(new Callback<WeatherBean>() {
//            @Override
//            public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
//                WeatherBean dataBean = response.body();
//            }
//
//            @Override
//            public void onFailure(Call<WeatherBean> call, Throwable t) {
//                System.out.println("123 123 123 " + t.toString());
//            }
//        });

        Request_Interface request_interface = RetrofitUtils.getInstance().create(Request_Interface.class);
        request_interface.getCity("北京").subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Response<WeatherBean>>() {
                    @Override
                    public void accept(Response<WeatherBean> weatherBeanResponse) throws Exception {
                        weatherBeanResponse.code();
                    }
                });
    }


    public void toLogin(View view) {
        Intent intent = new Intent(RetrofitActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
