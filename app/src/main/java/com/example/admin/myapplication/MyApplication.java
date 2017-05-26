package com.example.admin.myapplication;

import android.app.Application;

import com.facebook.soloader.SoLoader;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.weex.ImageAdapter;

/**
 * Created by maraonda admin on 2017/4/20.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, false);//FaceBook 那套框架

        inItWeex();
    }



    private void inItWeex(){
        InitConfig config = new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build();
        WXSDKEngine.initialize(this,config);
    }
}
