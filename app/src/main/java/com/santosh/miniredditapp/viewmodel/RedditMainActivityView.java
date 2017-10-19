package com.santosh.miniredditapp.viewmodel;


import com.santosh.miniredditapp.data.RedditNewResponse;


public interface RedditMainActivityView extends IView {

    void load(RedditNewResponse newsItems);

    void loadMore();

}
