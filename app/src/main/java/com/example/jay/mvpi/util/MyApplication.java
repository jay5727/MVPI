package com.example.jay.mvpi.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by Jay on 13/03/2018.
 */
//This is the entry point to the application
//Remember to register it in Manifest in application tag itself.
/*<application
        android:name="util.MyApplication"..........
*/
public class MyApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}