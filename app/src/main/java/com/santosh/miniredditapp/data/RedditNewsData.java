package com.santosh.miniredditapp.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sb152w on 10/16/17.
 */

public class RedditNewsData implements Serializable{

    @SerializedName("author")
    private String author;

    @SerializedName("title")
    private String title;

    @SerializedName("num_comments")
    private int numberOfComments;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("created")
    private Long createDate;

    @SerializedName("url")
    private String url;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(int numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
