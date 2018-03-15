package com.example.jay.mvpi.ui.login;

import android.text.TextUtils;

import com.example.jay.mvpi.model.LoginRequestModel;
import com.example.jay.mvpi.model.LoginResponseModel;
import com.example.jay.mvpi.util.Constants;
import com.example.jay.mvpi.util.InternetStatus;

/**
 * Created by Jay on 13/03/2018.
 */

//Not to access any Android related classes.eg:Context or Resources
public class LoginPresenter implements ILoginContract.ILoginPresenter, ILoginContract.ILoginInteractor.onLoginFinishedListener {

    private ILoginContract.ILoginView mView;
    private ILoginContract.ILoginInteractor mLoginInteractor;

    public LoginPresenter(ILoginContract.ILoginView mView) {
        this.mView = mView;
        mLoginInteractor = new LoginInteractor();
    }

    @Override
    public void validateCredentials(String username, String password) {
        if (mView != null) {
            if (TextUtils.isEmpty(username)) {
                mView.setUserNameError("Empty");
            } else if (!username.equals("jay5727")) {
                mView.setUserNameError("Invalid");

            } else if (TextUtils.isEmpty(password)) {
                mView.setPasswordError("Empty");
            } else if (!password.equals("123456")) {
                mView.setPasswordError("Invalid");

            } else {
                //valid
                mView.showProgressDialog();
                if (InternetStatus.checkConnection()) {
                    mLoginInteractor.doLoginRequest(new LoginRequestModel(username, password), this);
                } else
                    mView.showToast(Constants.NoInternet);
            }
        }
    }

    @Override
    public void onLoginSuccess(LoginResponseModel responseModel) {
        if (mView != null) {
            mView.hideProgressDialog();
            if (responseModel != null) {
                if (responseModel.getResponsecode() != null) {
                    mView.getLoginSuccess(responseModel.getResponsecode());
                } else {
                    mView.showToast(Constants.Error);
                }
            } else {
                mView.showToast(Constants.Error);
            }
        }
    }

  /*  @Override
    public void onDestroy() {
        if (mView != null) {
            mView = null;
        }
    }*/

    @Override
    public void onFailure(String message) {
        if (mView != null) {
            mView.hideProgressDialog();
            mView.showToast(Constants.Error);
        }
    }
}