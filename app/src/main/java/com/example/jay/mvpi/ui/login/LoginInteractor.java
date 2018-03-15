package com.example.jay.mvpi.ui.login;

import com.example.jay.mvpi.model.LoginRequestModel;
import com.example.jay.mvpi.model.LoginResponseModel;
import com.example.jay.mvpi.rest.ApiClient;
import com.example.jay.mvpi.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jay on 13/03/2018.
 */
public class LoginInteractor implements ILoginContract.ILoginInteractor {

    @Override
    public void doLoginRequest(LoginRequestModel obj ,final onLoginFinishedListener listener) {
        try {
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

            Call<LoginResponseModel> call = apiService.Authenticate();

            call.enqueue(new Callback<LoginResponseModel>() {
                @Override
                public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                    if (response.isSuccessful()) {
                        listener.onLoginSuccess(response.body());
                    } else {
                        listener.onFailure("Failure");
                    }
                }
                @Override
                public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                        listener.onFailure("Failure");
                }
            });

        } catch (Exception ex) {
            listener.onFailure("Failure");
        }
    }
}