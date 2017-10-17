package com.santosh.miniredditapp.di.module;

import android.content.Context;

import com.santosh.miniredditapp.MiniRedditApplication;
import com.santosh.miniredditapp.di.annotation.ForApplication;
import com.santosh.miniredditapp.network.RedditAPIService;
import com.santosh.miniredditapp.presenter.RedditHomePresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private MiniRedditApplication miniRedditApplication;

    public AppModule(MiniRedditApplication miniRedditApplication) {
        this.miniRedditApplication = miniRedditApplication;
    }

    @Provides
    @ForApplication
    @Singleton
    Context provideApplicationContext(){
        return  miniRedditApplication.getBaseContext();
    }

    @Provides
    @Singleton
    RedditHomePresenter provideMainPresenter(RedditAPIService redditAPIService){
        return new RedditHomePresenter(redditAPIService);
    }
}
