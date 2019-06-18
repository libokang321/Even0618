package com.bawei.net;

import com.bawei.api.Api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 *@Auther:姓名
 *@Date: 时间
 *@Description:功能
 * */
public class RetrofitUtils {
    private static RetrofitUtils utils;
    private final Retrofit retrofit;

    private RetrofitUtils() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //日志拦截器
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * 双重检验锁
     * @return
     */
    public static RetrofitUtils getUtils() {
        if (utils == null) {
            synchronized (RetrofitUtils.class){
                if (utils == null){
                    utils = new RetrofitUtils();
                }
            }
        }
        return utils;
    }
    /**
     *  动态代理
     */
    public <T> T createService(Class<T> cls){
        return retrofit.create(cls);
    }

 }
