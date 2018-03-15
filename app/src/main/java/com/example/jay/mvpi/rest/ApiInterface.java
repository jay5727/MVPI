package com.example.jay.mvpi.rest;

import com.example.jay.mvpi.model.LoginResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("v2/5aa7f9042f0000d9328ea84c")
    Call<LoginResponseModel> Authenticate();
}

