package com.google.itubeapp;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import org.litepal.LitePal;

/**
 * global configuration
 */
public class MyApplication extends Application implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Initialize LitePal
        LitePal.initialize(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
