package com.santosh.miniredditapp.presenter;


import com.santosh.miniredditapp.network.RedditAPIService;

import javax.inject.Inject;

public class RedditHomePresenter {

    private RedditAPIService redditAPIService;

    @Inject
    public RedditHomePresenter(RedditAPIService redditAPIService) {
        this.redditAPIService = redditAPIService;
    }


}
