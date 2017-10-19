package com.santosh.miniredditapp.model;


import com.santosh.miniredditapp.data.RedditNewsResponse;

/**
 * Interface model to save all the RedditNewsResponse items as we scroll through the list
 */

public interface ILoadmoreRedditItemsModel {

    RedditNewsResponse getMoreRedditItems();

    void setMoreRedditItems(RedditNewsResponse redditItems);

}
