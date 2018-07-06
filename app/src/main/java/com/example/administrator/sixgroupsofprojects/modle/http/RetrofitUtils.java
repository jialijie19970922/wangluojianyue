package com.example.administrator.sixgroupsofprojects.modle.http;

import android.util.Log;


import com.example.administrator.sixgroupsofprojects.modle.interceptor.TokenInterceptord;
import com.example.administrator.sixgroupsofprojects.modle.interfaces.RetorfitApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * fengzhuang
 * Created by jlj on 2018/6/8.
 */

public class RetrofitUtils {

    static {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("TEST_jlj_message", "ApiServiceHelper.createApiService().HttpLoggingInterceptor.log().message -> " + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new TokenInterceptord()) // 这个会全部打印出来
                .build();
        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(ApiUtils.BaseURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        retorfitApi = retrofit.create(RetorfitApi.class);
    }
    public static RetorfitApi retorfitApi;
}