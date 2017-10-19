package com.santosh.miniredditapp.model;


import com.santosh.miniredditapp.data.RedditNewResponse;


public interface ILoadmoreRedditItemsModel {

    RedditNewResponse getMoreRedditItems();

    void setMoreRedditItems(RedditNewResponse redditItems);

}
