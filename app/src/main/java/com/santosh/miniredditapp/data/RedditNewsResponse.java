package com.santosh.miniredditapp.data;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RedditNewsResponse implements Serializable{

    @SerializedName("data")
    private RedditResponseData redditResponseData;

    public RedditResponseData getRedditResponseData() {
        return redditResponseData;
    }

    public void setRedditResponseData(RedditResponseData redditResponseData) {
        this.redditResponseData = redditResponseData;
    }
}
