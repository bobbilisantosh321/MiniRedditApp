package com.santosh.miniredditapp.di.module;

import android.content.Context;

import com.santosh.miniredditapp.MiniRedditApplication;
import com.santosh.miniredditapp.di.annotation.ForApplication;
import com.santosh.miniredditapp.di.scope.ApplicationScope;
import com.santosh.miniredditapp.model.ILoadmoreRedditItemsModel;
import com.santosh.miniredditapp.model.LoadMoreRedditItemsModel;
import com.santosh.miniredditapp.network.RedditAPIService;
import com.santosh.miniredditapp.viewmodel.RedditMainActivityViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private MiniRedditApplication miniRedditApplication;

    public AppModule(MiniRedditApplication miniRedditApplication) {
        this.miniRedditApplication = miniRedditApplication;
    }

    @Provides //This annotation means that method below provides dependency
    @ForApplication
    @ApplicationScope
    Context provideApplicationContext(){
        return  miniRedditApplication.getBaseContext();
    }

    @Provides
    @ApplicationScope
    ILoadmoreRedditItemsModel provideLoadMoreRedditItemsModel(){
        return new LoadMoreRedditItemsModel();
    }

    @Provides
    @ApplicationScope
    RedditMainActivityViewModel provideMainActivityViewModel(RedditAPIService redditAPIService, ILoadmoreRedditItemsModel loadmoreRedditItemsModel){
        return new RedditMainActivityViewModel(redditAPIService,loadmoreRedditItemsModel);
    }

}
