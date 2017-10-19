package com.santosh.miniredditapp.di.module;

import android.content.Context;

import com.santosh.miniredditapp.R;
import com.santosh.miniredditapp.di.annotation.ForApplication;
import com.santosh.miniredditapp.network.RedditAPIService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class BackendModule {

    @Provides
    @Singleton
    RedditAPIService provideRedditApi(@ForApplication Context context, OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory){

        OkHttpClient.Builder httpClient = okHttpClient.newBuilder();

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            HttpUrl originalHttpUrl = original.url();

            Request.Builder requestBuilder = original.newBuilder().url(originalHttpUrl);

            Request request = requestBuilder.build();

            return chain.proceed(request);
        });

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .baseUrl(context.getString(R.string.reddit_news_base_url))
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(RedditAPIService.class);

    }


    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(){
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    GsonConverterFactory providesGsonConverterFactory(){
        return GsonConverterFactory.create();
    }
}
