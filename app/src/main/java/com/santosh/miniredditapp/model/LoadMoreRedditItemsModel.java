package com.santosh.miniredditapp.model;

import com.santosh.miniredditapp.data.RedditNewResponse;

import javax.inject.Inject;


public class LoadMoreRedditItemsModel implements ILoadmoreRedditItemsModel {

    RedditNewResponse redditNewResponse;

    @Override
    public RedditNewResponse getMoreRedditItems() {
        return redditNewResponse;
    }

    @Override
    public void setMoreRedditItems(RedditNewResponse redditItems) {
        this.redditNewResponse = redditItems;
    }
}
