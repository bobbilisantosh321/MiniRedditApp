package com.santosh.miniredditapp.viewmodel;


import com.santosh.miniredditapp.data.RedditNewsResponse;


public interface RedditMainActivityView extends IView {

    /**
     * load(newItems) is used to update the views for the first page
     * @param newsItems
     */
    void load(RedditNewsResponse newsItems);

    /**
     * loadMore() is triggered when user scroll beyound one page (first 10 items in this case)
     */
    void loadMore();

}
