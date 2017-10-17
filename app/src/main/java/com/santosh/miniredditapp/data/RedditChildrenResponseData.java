package com.santosh.miniredditapp.data;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RedditChildrenResponseData implements Serializable {

    @SerializedName("data")
    private RedditNewsData redditNewsData;

    public RedditNewsData getRedditNewsData() {
        return redditNewsData;
    }

    public void setRedditNewsData(RedditNewsData redditNewsData) {
        this.redditNewsData = redditNewsData;
    }
}
