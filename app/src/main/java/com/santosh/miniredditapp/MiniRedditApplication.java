package com.santosh.miniredditapp;


import android.app.Application;

import com.santosh.miniredditapp.di.component.AppComponent;
import com.santosh.miniredditapp.di.factory.AppComponentFactory;

import timber.log.Timber;

public class MiniRedditApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        buildComponentGraph();
        if(BuildConfig.DEBUG){
            //Timber is a logging tool with a small, extensible API which provides utility on top of Android's normal Log class.
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void buildComponentGraph() {
        appComponent = AppComponentFactory.create(this);
        appComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
