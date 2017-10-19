package com.santosh.miniredditapp.util;

public class UserFriendlyTimeConverterUtil {

    //Util class to convert EPOCH time to human readable time

    private static UserFriendlyTimeConverterUtil instance;

    private UserFriendlyTimeConverterUtil() {
    }

    public static UserFriendlyTimeConverterUtil getInstance() {

        if(instance == null){
            instance = new UserFriendlyTimeConverterUtil();
        }

        return instance;
    }

    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;
    private static final int MONTHS_MILLIS = 30 * DAY_MILLIS;
    private static final int YEARS_MILLIS = 12 * MONTHS_MILLIS;
    StringBuffer sb;


    public String getTimeAgo(long time) {
        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }

        long now = getCurrentTime();
        if (time <= 0) {
            return null;
        }

        final long diff = now - time;

        sb = new StringBuffer("");

        if (diff <= SECOND_MILLIS) {
            sb.append("just now");
        } else if (diff < 2 * MINUTE_MILLIS) {
            sb.append("a minute ago");
        } else if (diff < 50 * MINUTE_MILLIS) {
            sb.append(diff / MINUTE_MILLIS + " minutes ago");
        } else if (diff < 90 * MINUTE_MILLIS) {
            sb.append("an hour ago");
        } else if (diff < 24 * HOUR_MILLIS) {
            sb.append(diff / HOUR_MILLIS + " hours ago");
        } else if (diff < 48 * HOUR_MILLIS) {
            sb.append("yesterday");
        } else if(diff > 2* DAY_MILLIS){
            sb.append(diff / DAY_MILLIS + " days ago");
        } else if(diff == MONTHS_MILLIS){
            sb.append("a month ago");
        } else if(diff > MONTHS_MILLIS){
            sb.append(diff/MONTHS_MILLIS + " months ago");
        } else if(diff == YEARS_MILLIS){
            sb.append("a year ago");
        } else if(diff > YEARS_MILLIS){
            sb.append(diff/YEARS_MILLIS + " years ago");
        }
        else {
            sb.append(diff/YEARS_MILLIS + " years" + diff/MONTHS_MILLIS + " months ago");
        }

        return sb.toString();
    }

    public Long getCurrentTime(){

        long currentTime = System.currentTimeMillis();

        return currentTime;
    }
}
