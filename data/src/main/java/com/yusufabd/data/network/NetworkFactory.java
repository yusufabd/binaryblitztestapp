package com.yusufabd.data.network;

import com.yusufabd.data.Config;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yusufabd on 9/8/2017.
 */

public class NetworkFactory {
    private static Retrofit sRetrofit;
    private static OkHttpClient sClient;
    private static NetworkService sService;

    public static NetworkService getService(){
        if (sService == null){
            sService = getRetrofit(Config.BASE_URL).create(NetworkService.class);
        }
        return sService;
    }

    public static NetworkService getAWSService(){
        if (sService == null){
            sService = getRetrofit(Config.AWS_BASE_URL).create(NetworkService.class);
        }
        return sService;
    }

    private static Retrofit getRetrofit(String baseUrl){
        if (sRetrofit == null){
            sRetrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .client(getClient())
                    .build();
        }
        return sRetrofit;
    }

    private static OkHttpClient getClient(){
        if (sClient == null){
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            sClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();
        }
        return sClient;
    }
}
