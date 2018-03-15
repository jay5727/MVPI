package com.example.jay.mvpi.ui.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jay.mvpi.R;
import com.example.jay.mvpi.util.Constants;
import com.example.jay.mvpi.ui.WelcomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ILoginContract.ILoginView {

    @BindView(R.id.text_input_username)
    TextInputLayout text_input_username;

    @BindView(R.id.input_username)
    EditText input_username;

    @BindView(R.id.text_input_password)
    TextInputLayout text_input_password;

    @BindView(R.id.input_password)
    EditText input_password;

    @BindView(R.id.btn_login)
    Button btn_login;

    ILoginContract.ILoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        mLoginPresenter = new LoginPresenter(this);
    }

    @OnClick(R.id.btn_login)
    void onLoginClicked() {
        mLoginPresenter.validateCredentials
                (input_username.getText().toString().trim(), input_password.getText().toString().trim());
    }


    @Override
    public void setUserNameError(String msg) {
        clearErrorMessage();
        text_input_username.setFocusable(true);
        text_input_username.setFocusableInTouchMode(true);
        text_input_username.setError(msg.equals("Empty") ? getString(R.string.Mandatory) : getString(R.string.InvalidUserName));
        text_input_username.requestFocus();
    }

    @Override
    public void setPasswordError(String msg) {
        clearErrorMessage();
        text_input_password.setFocusable(true);
        text_input_password.setFocusableInTouchMode(true);
        text_input_password.setError(msg.equals("Empty") ? getString(R.string.Mandatory) : getString(R.string.InvalidPassword));
        text_input_password.requestFocus();
    }

    @Override
    public void getLoginSuccess(String responsecode) {
        clearErrorMessage();
        //Toast.makeText(this, responsecode, Toast.LENGTH_SHORT).show();
        if (responsecode.equals("1"))
            startActivity(new Intent(LoginActivity.this, WelcomeActivity.class));
        else
            showToast(Constants.Error);
    }

    //region ProgressDialog
    public ProgressDialog progressDialog;

    @Override
    public void showProgressDialog() {
        try {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMax(100);
            // progressDialog.setMessage("");
            progressDialog.setMessage("Loading...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        } catch (Exception e) {
        }
    }

    @Override
    public void hideProgressDialog() {
        try {
            progressDialog.dismiss();
        } catch (Exception e) {
        }
    }
    //endregion

    @Override
    public void showToast(String key) {
        Toast.makeText(this, getStringResourceByName(key), Toast.LENGTH_SHORT).show();
    }

    private String getStringResourceByName(String aString) {
        try {
            String packageName = getPackageName();
            int resId = getResources().getIdentifier(aString, "string", packageName);
            return getString(resId);
        } catch (Exception ex) {
            return aString;
        }
    }

    void clearErrorMessage() {
        text_input_username.setError(null);
        text_input_password.setError(null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
            return super.onTouchEvent(event);
        } catch (Exception e) {
            return true;
        }
    }
}