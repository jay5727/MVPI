package com.example.jay.mvpi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jay on 13-03-2018.
 */

public class LoginResponseModel {

    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("responsecode")
    @Expose
    private String responsecode;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResponsecode() {
        return responsecode;
    }

    public void setResponsecode(String responsecode) {
        this.responsecode = responsecode;
    }
}
