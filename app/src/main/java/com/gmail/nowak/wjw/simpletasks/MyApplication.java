package com.gmail.nowak.wjw.simpletasks;

import android.app.Application;

import timber.log.Timber;

public class MyApplication extends Application {
    public AppGraph appGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new MyTagDebugTree());
        }
        appGraph = new AppGraph(this);

    }
}
