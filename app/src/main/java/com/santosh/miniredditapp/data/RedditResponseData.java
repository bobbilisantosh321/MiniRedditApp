package com.santosh.miniredditapp.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class RedditResponseData implements Serializable {

    @SerializedName("children")
    private List<RedditChildrenResponseData> redditChildrenResponseList;

    @SerializedName("after")
    private String after;

    @SerializedName("before")
    private String before;

    public List<RedditChildrenResponseData> getRedditChildrenResponseList() {
        return redditChildrenResponseList;
    }

    public void setRedditChildrenResponseList(List<RedditChildrenResponseData> redditChildrenResponseList) {
        this.redditChildrenResponseList = redditChildrenResponseList;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }
}
