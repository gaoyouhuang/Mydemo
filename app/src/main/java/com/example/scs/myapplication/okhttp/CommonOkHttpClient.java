package com.example.scs.myapplication.okhttp;

import android.content.Context;

import com.example.scs.myapplication.okhttp.cookie.SimpleCookieJar;
import com.example.scs.myapplication.okhttp.interceptor.LogInterceptor;
import com.example.scs.myapplication.okhttp.listener.DisposeDataHandle;
import com.example.scs.myapplication.okhttp.response.CommonFileCallback;
import com.example.scs.myapplication.okhttp.response.CommonJsonCallback;
import com.example.scs.myapplication.okhttp.ssl.HttpsUtils;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author vision
 * @function 用来发送get, post请求的工具类，包括设置一些请求的共用参数
 */
//    implementation("com.squareup.okhttp3:okhttp:3.12.0")
//            implementation 'com.google.code.gson:gson:2.7'
//https://blog.csdn.net/jackingzheng/article/details/51778793#11-%E8%AF%B7%E6%B1%82
public class CommonOkHttpClient {
    private static final int TIME_OUT = 30;
    private static OkHttpClient mOkHttpClient = null;
    // private static CommonOkHttpClient mClient = null;

    public static void getInstance(Context context) {
        if (mOkHttpClient != null)
            return;
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
		okHttpClientBuilder.addInterceptor(new LogInterceptor()); //应用拦截
//		okHttpClientBuilder.addNetworkInterceptor(new LogInterceptor()); //网络拦截
        okHttpClientBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        okHttpClientBuilder.cookieJar(new SimpleCookieJar(context));
        okHttpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.followRedirects(true);
        /**
         * trust all the https point
         */
//		okHttpClientBuilder.sslSocketFactory(HttpsUtils.getSslSocketFactory());
        mOkHttpClient = okHttpClientBuilder.build();
    }

    /**
     * 指定cilent信任指定证书
     *
     * @param certificates
     */
    public static void setCertificates(InputStream... certificates) {
        mOkHttpClient.newBuilder().sslSocketFactory(HttpsUtils.getSslSocketFactory(certificates, null, null)).build();
    }

    /**
     * 指定client信任所有证书
     */
    public static void setCertificates() {
        mOkHttpClient.newBuilder().sslSocketFactory(HttpsUtils.getSslSocketFactory());
    }

    /**
     * 通过构造好的Request,Callback去发送请求
     *
     * @param request
     */
    public static Call get(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    public static Call post(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    public static Call downloadFile(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonFileCallback(handle));
        return call;
    }
}