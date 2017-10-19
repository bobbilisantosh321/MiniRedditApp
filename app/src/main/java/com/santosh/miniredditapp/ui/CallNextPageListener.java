package com.santosh.miniredditapp.ui;


import com.santosh.miniredditapp.data.RedditNewsResponse;

/**
 * Call back mechanism triggered when user scrolled through the ten items
 */
public interface CallNextPageListener {
    /**
     * New page data is fetched in this callback
     * @param redditNewsResponse
     */
    void callNextPage(RedditNewsResponse redditNewsResponse);
}
