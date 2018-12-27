package com.example.scs.myapplication.okhttp.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LogInterceptor implements Interceptor {
    private  static final String TAG = "HttpInterceptor";
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Log.i(TAG,request.url()==null?"":"request.url()"+request.url().toString());
        Log.i(TAG, chain.connection()==null?"":"chain.connection()"+chain.connection().toString());
        Log.i(TAG,request.headers()==null?"":"request.headers()"+request.headers().toString());

        //还可以通过拦截器去重写请求 重新设置请求的地址 加密body等
//        Request.Builder newBuilder = request.newBuilder().header("cookie", "userToken=" + SharedPreferencesManager.getInstance().getUserTken()); // <-- this is the important line
//        Request request1 = newBuilder.build();


        HttpUrl url = request.url();
        //http://127.0.0.1/test/upload/img?userName=xiaoming&userPassword=12345
        String scheme = url.scheme();//  http https
        String host = url.host();//   127.0.0.1
        String path = url.encodedPath();//  /test/upload/img
        String query = url.encodedQuery();//  userName=xiaoming&userPassword=12345

        Log.i(TAG,"scheme"+scheme);
        Log.i(TAG,"host"+host);
        Log.i(TAG,"path"+path);
        Log.i(TAG,"query"+query);

        Response response = chain.proceed(request);
        Log.i(TAG,response.request().url()==null?"":"response.request().url()"+response.request().url().toString());
        Log.i(TAG,response.headers()==null?"":"response.headers()"+response.headers().toString());


        if (response != null) {
            ResponseBody responseBody = response.body();
            long contentLength = responseBody.contentLength();
            String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";

            Log.i(TAG,response.code() + ' '
                    + response.message() + ' '
                    + response.request().url()+' '
                    + bodySize
            );

            Headers headers = response.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                Log.i(TAG,headers.name(i) + ": " + headers.value(i));
            }


        }

        return response;
    }
}
