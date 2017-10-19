package com.santosh.miniredditapp.di.factory;


import com.santosh.miniredditapp.MiniRedditApplication;
import com.santosh.miniredditapp.di.component.AppComponent;
import com.santosh.miniredditapp.di.component.DaggerAppComponent;
import com.santosh.miniredditapp.di.module.AppModule;


/**
 * Factory helper class used to build the dependecy graph.
 */
public final class AppComponentFactory {

    private AppComponentFactory(){

    }

    public static AppComponent create(MiniRedditApplication app){

        return DaggerAppComponent
                .builder()
                .appModule(new AppModule(app))
                .build();

    }
}
