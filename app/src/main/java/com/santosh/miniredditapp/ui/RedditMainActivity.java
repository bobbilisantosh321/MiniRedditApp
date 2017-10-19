package com.santosh.miniredditapp.ui;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.santosh.miniredditapp.BaseActivity;
import com.santosh.miniredditapp.MiniRedditApplication;
import com.santosh.miniredditapp.R;
import com.santosh.miniredditapp.adapter.RedditNewsAdapter;
import com.santosh.miniredditapp.data.RedditNewsResponse;
import com.santosh.miniredditapp.databinding.ActivityRedditMainBinding;
import com.santosh.miniredditapp.model.ILoadmoreRedditItemsModel;
import com.santosh.miniredditapp.network.RedditAPIService;
import com.santosh.miniredditapp.viewmodel.RedditMainActivityView;
import com.santosh.miniredditapp.viewmodel.RedditMainActivityViewModel;

import javax.inject.Inject;

public class RedditMainActivity extends BaseActivity<ActivityRedditMainBinding,RedditMainActivityViewModel> implements CallNextPageListener, RedditMainActivityView {

    private RedditNewsResponse redditNewsResponse;
    private String REDDIT_NEWS_KEY = "REDDIT_NEWS_KEY";
    private String PAGE_LIMIT = "10";

    RedditNewsAdapter redditNewsAdapter;
    LinearLayoutManager layoutManager;

    //this will basically call new RedditAPIService();
    @Inject
    RedditAPIService redditAPIService;

    @Inject
    ILoadmoreRedditItemsModel loadmoreRedditItemsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MiniRedditApplication) getApplication()).getAppComponent().inject(this);

        viewModel = new RedditMainActivityViewModel(redditAPIService,loadmoreRedditItemsModel);
        viewModel.attach(this);

        bindView(R.layout.activity_reddit_main);
        binding.setIsLoading(true);

        redditNewsAdapter = new RedditNewsAdapter(this);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        binding.redditNewsRecyclerView.setLayoutManager(layoutManager);
        binding.redditNewsRecyclerView.setAdapter(redditNewsAdapter);

        //Restoring the data on orientation changes
        if(savedInstanceState != null && savedInstanceState.containsKey(REDDIT_NEWS_KEY) ){
            redditNewsResponse = (RedditNewsResponse) savedInstanceState.getSerializable(REDDIT_NEWS_KEY);
            load(redditNewsResponse);
        }else{
            //First launch network call is made to fetch data with empty  "after" value in the request.
            // This gives back the data in first page with limit "10"
            viewModel.fetchRedditNewsFirstPage();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * Restoring the data on orientation changes
     * @param outState
     */

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(redditNewsResponse != null){
            outState.putSerializable(REDDIT_NEWS_KEY, redditNewsResponse);
        }
    }


    /**
     * When user reaches 10th item on the list, this is triggered.
     * Fetches subsequent pages passing "after" value from the Reddit respone.
     * @param redditNewsResponse
     */
    @Override
    public void callNextPage(RedditNewsResponse redditNewsResponse) {
        binding.setIsLoading(true);
        viewModel.fetchRedditNewsSubsequentPages(loadmoreRedditItemsModel.getMoreRedditItems().getRedditResponseData().getAfter(),PAGE_LIMIT);
    }

    /**
     * load(newItems) is used to update the views for the first page
     * @param newsItems
     */
    @Override
    public void load(RedditNewsResponse newsItems) {
        redditNewsResponse = newsItems;
        binding.setIsLoading(false);
        redditNewsAdapter.setRedditNewResponse(newsItems.getRedditResponseData().getRedditChildrenResponseList());

        binding.redditNewsRecyclerView.setHasFixedSize(true);
        binding.redditNewsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.redditNewsRecyclerView.setHasFixedSize(true);
        binding.redditNewsRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(newsItems,layoutManager,this));

    }

    /**
     * loadMore() is triggered when user scroll beyound one page (first 10 items in this case)
     */
    @Override
    public void loadMore() {
        if(loadmoreRedditItemsModel != null){
            binding.setIsLoading(false);
            redditNewsResponse = loadmoreRedditItemsModel.getMoreRedditItems();
            redditNewsAdapter.setRedditNewResponse(loadmoreRedditItemsModel.getMoreRedditItems().getRedditResponseData().getRedditChildrenResponseList());
        }
    }


}
