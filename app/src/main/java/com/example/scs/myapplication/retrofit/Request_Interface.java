package com.example.scs.myapplication.retrofit;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by scs on 18-3-6.
 */

public interface Request_Interface {

    @GET("api/weather/json.shtml")
    Observable<Response<WeatherBean>> getCity(@Query("city") String city);//api/weather/json.shtml?city=北京 (1)
//    Observable<WeatherBean> getCity(@Query("city") String city); (2)
//    Observable<retrofit2.adapter.rxjava.Result<Result<WeatherBean>>> getCity(@Query("city") String city); (3)
    //方法1　无法获取到header　方法2 3 可以获取到header头

    @GET("api/weather/json.shtm/{city}")
    Call<ResponseBody> getCity1(@Part("city") String city);//api/weather/json.shtml/北京

    @FormUrlEncoded
    @POST("api/weather/json.shtml")
    Call<ResponseBody> postCity2(@Field("city") String city);//api/weather/json.shtml?city=北京

    @Multipart
    @POST("api/weather/json.shtml")
    Call<ResponseBody> postCity3(@Path("city") String city, MultipartBody.Part file);//api/weather/json.shtml?city=北京

    @FormUrlEncoded
    @POST("http://api.mhj1688.com/AppUserManageV2/login2")
    Observable<String> login(@FieldMap Map<String, String> options);

    @POST("http://api.mhj1688.com/AppUserManageV2/getUserScenes")
    Observable<ResponseBody>getUserScenes();

}
