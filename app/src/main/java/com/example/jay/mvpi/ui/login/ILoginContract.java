package com.example.jay.mvpi.ui.login;

import com.example.jay.mvpi.model.LoginRequestModel;
import com.example.jay.mvpi.model.LoginResponseModel;

/**
 * Created by Jay on 13/03/2018.
 */

public interface ILoginContract {
    //Since we are using ApiModels directly we don't need to create new one here...!
    interface IModel {

    }

    //these methods are overridden in Activity/Fragment.
    interface ILoginView {
        void showToast(String key);

        void setUserNameError(String msg);

        void setPasswordError(String msg);

        //Step 4
        //presenter sends appropriate response to View i.e Activity/Fragment
        void getLoginSuccess(String responsecode);

        void showProgressDialog();

        void hideProgressDialog();
    }

    //STEP 1
    //View is sending request to presenter by saying mLoginPresenter.validateCredentials("","") from Activity/Fragment

    interface ILoginPresenter {
        void validateCredentials(String username, String password);

        //void onDestroy();
    }
    //STEP 3
    // Interactor sends data back to presenter... saying listener.onLoginSuccess() or listener.onFailure.
    //Interactor is responsible for getting data from the server & sends to presenter via onLoginSuccess/onFailure...!
    interface ILoginInteractor
    {
        //These 2 methods are overridden in Presenter.
        interface onLoginFinishedListener {
            void onLoginSuccess(LoginResponseModel responseModel);
            void onFailure(String message);
        }
        //STEP 2
        //if everything is validated in presenter then request is made to Interactor.
        //by  mLoginInteractor.doLoginRequest(new LoginRequestModel(username, password), this);

        void doLoginRequest(LoginRequestModel obj ,onLoginFinishedListener listener);
    }
}