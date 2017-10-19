package com.santosh.miniredditapp.model;

import com.santosh.miniredditapp.data.RedditNewsResponse;

/**
 * Model class to save all the RedditNewsResponse items as we scroll through the list
 */
public class LoadMoreRedditItemsModel implements ILoadmoreRedditItemsModel {

    RedditNewsResponse redditNewsResponse;

    @Override
    public RedditNewsResponse getMoreRedditItems() {
        return redditNewsResponse;
    }

    @Override
    public void setMoreRedditItems(RedditNewsResponse redditItems) {
        this.redditNewsResponse = redditItems;
    }
}
