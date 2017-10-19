package com.santosh.miniredditapp.ui;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.santosh.miniredditapp.data.RedditNewResponse;

public class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener{

    LinearLayoutManager linearLayoutManager;
    CallNextPageListener callNextPageListener;
    RedditNewResponse redditNewResponse;
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 1;
    private int firstVisibleItem = 0;
    private int visibleItemCount = 0;
    private int totalItemCount = 0;
    private int pageCounter = 1;
    private int NO_OF_PAGES = 5;

    public EndlessRecyclerViewScrollListener(RedditNewResponse redditNewResponse, LinearLayoutManager linearLayoutManager, CallNextPageListener callNextPageListener) {
        this.linearLayoutManager = linearLayoutManager;
        this.callNextPageListener = callNextPageListener;
        this.redditNewResponse = redditNewResponse;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if(dy > 0){
            visibleItemCount = recyclerView.getChildCount();
            totalItemCount = linearLayoutManager.getItemCount();
            firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false;
                    previousTotal = totalItemCount;
                }
            }

            if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                pageCounter++;
                //Restriciting pagesize to 5
                // As per the requirement : create a simple Reddit client that shows the top 50 entries
                if(pageCounter <= NO_OF_PAGES)
                callNextPageListener.callNextPage(redditNewResponse);
                loading = true;
            }

        }
    }

}
