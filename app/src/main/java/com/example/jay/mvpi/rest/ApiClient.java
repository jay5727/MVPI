package com.example.jay.mvpi.rest;

import com.example.jay.mvpi.util.CustomHttpLogging;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jay on 13-03-18.
 */
public class ApiClient {

    public static final String BASE_URL = "http://www.mocky.io/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(customLogInterceptor())//added
                    .build();
        }
        return retrofit;
    }

    private static okhttp3.OkHttpClient  customLogInterceptor()
    {
        //TO LOG RETROFIT API CALLS
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new CustomHttpLogging());
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient();

        okHttpClient = okHttpClient.newBuilder()
                .addInterceptor(loggingInterceptor)//
                .connectTimeout(100, TimeUnit.SECONDS)//
                .readTimeout(100, TimeUnit.SECONDS)//
                .build();

        return okHttpClient;
    }
}