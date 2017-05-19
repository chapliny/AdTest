package com.example.admin.myapplication;

import android.app.Application;

import com.facebook.soloader.SoLoader;

/**
 * Created by maraonda admin on 2017/4/20.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, false);
    }
}
