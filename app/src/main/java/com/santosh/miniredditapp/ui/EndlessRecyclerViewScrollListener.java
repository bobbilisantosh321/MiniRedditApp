package com.santosh.miniredditapp.ui;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.santosh.miniredditapp.data.RedditNewsResponse;

public class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener{

    LinearLayoutManager linearLayoutManager;
    CallNextPageListener callNextPageListener;
    RedditNewsResponse redditNewsResponse;

    // The total number of items in the dataset after the last load
    private int previousTotal = 0;

    // True if we are still waiting for the last set of data to load.
    private boolean loading = true;

    // The minimum number of items to have below your current scroll position before loading more.
    private int visibleThreshold = 1;


    private int firstVisibleItem = 0;


    private int visibleItemCount = 0;


    private int totalItemCount = 0;

    // The current offset index of data you have loaded
    private int pageCounter = 1;


    private int NO_OF_PAGES = 5;

    /**
     *
     * Supporting only LinearLayoutManager for now.
     * @param linearLayoutManager
     * @param redditNewsResponse
     * @param linearLayoutManager
     * @param callNextPageListener
     */

    public EndlessRecyclerViewScrollListener(RedditNewsResponse redditNewsResponse, LinearLayoutManager linearLayoutManager, CallNextPageListener callNextPageListener) {
        this.linearLayoutManager = linearLayoutManager;
        this.callNextPageListener = callNextPageListener;
        this.redditNewsResponse = redditNewsResponse;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if(dy > 0){
            visibleItemCount = recyclerView.getChildCount();
            totalItemCount = linearLayoutManager.getItemCount();
            firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

            // If it's still loading, we check to see if the dataset count has
            // changed, if so we conclude it has finished loading and update the current page
            // number and total item count.
            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false;
                    previousTotal = totalItemCount;
                }
            }

            // If it isn't currently loading, we check to see if we have reached
            // the visibleThreshold and need to reload more data.
            // If we do need to reload some more data, we execute callNextPage to fetch the next page data.

            if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                pageCounter++;
                //Restriciting pagesize to 5
                // As per the requirement : create a simple Reddit client that shows the top 50 entries
                if(pageCounter <= NO_OF_PAGES)
                callNextPageListener.callNextPage(redditNewsResponse);
                loading = true;
            }

        }
    }

}
