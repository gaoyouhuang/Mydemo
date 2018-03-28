package com.example.scs.myapplication.retrofit.util;

import android.util.Log;

import com.example.scs.myapplication.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by scs on 18-3-10.
 */

public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        //可以在拦截器里添加header头
//        Request request = chain.request();
//        okhttp3.Response response = chain.proceed(request);
//        if (TextUtils.isEmpty("")) {
//            return response.newBuilder()
//                    .build();
//        } else {
//            return response.newBuilder()
//                    .header("Bear", "")
//                    .removeHeader("Pragma")
//                    .build();
//        }

        Request request = chain.request();
        if (BuildConfig.DEBUG) {
            Log.i("HttpLog", String.format("发送请求 %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));
        }
        Request.Builder requestBuilder = request.newBuilder()
                .header("cookie", "userToken=087394d3404d5435802f66bec8ebe1c3"); // <-- this is the important line

        Request request1 = requestBuilder.build();
        return chain.proceed(request1);
    }
}
