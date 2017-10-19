package com.santosh.miniredditapp.databinder;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.santosh.miniredditapp.data.RedditNewsData;

public class OnClickHandler {

    private final RedditNewsData redditNewsData;
    private final Context context;
    private final String SELF_THUMBNAIL = "self";

    /**
     * This class provides the logic for onclick of thumbnail.
     * @param redditNewsData
     * @param context
     */

    public OnClickHandler(RedditNewsData redditNewsData, Context context) {
        this.redditNewsData = redditNewsData;
        this.context = context;
    }

    public void onClick(View view){
        //As per the requirement: for those having a picture (besides the thumbnail) ,
        // please allow the user to tap on the thumbnail to be sent to the full sized picture
        // (so just opening the URL would be OK).
        // ASSUMPTION: Assuming thumbnails with data "self" are not clickable.
        if(redditNewsData != null && !SELF_THUMBNAIL.equalsIgnoreCase(redditNewsData.getThumbnail())){
            String url = redditNewsData.getUrl();
            Intent openUrlIntent = new Intent(Intent.ACTION_VIEW);
            openUrlIntent.setData(Uri.parse(url));
            context.startActivity(openUrlIntent);
        }
    }
}
