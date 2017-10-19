package com.santosh.miniredditapp.databinder;


import com.santosh.miniredditapp.data.RedditNewsData;
import com.santosh.miniredditapp.util.UserFriendlyTimeConverterUtil;

/**
 * This is a helper class to convert EPOC time to human readable time ("x hours ago" format)
 * This is invoked directly from the XML using data binding.
 */

public class CreatedTimeConvertorHandler {
    private final RedditNewsData redditNewsData;

    public CreatedTimeConvertorHandler(RedditNewsData redditNewsData) {
        this.redditNewsData = redditNewsData;
    }

    public String convertEpochToHumanReadableTime(){
        String humanReadableTime = UserFriendlyTimeConverterUtil.getInstance().getTimeAgo(redditNewsData.getCreateDate());
        return humanReadableTime;
    }
}
