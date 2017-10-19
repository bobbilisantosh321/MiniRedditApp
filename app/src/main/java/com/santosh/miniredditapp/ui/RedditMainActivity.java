package com.santosh.miniredditapp.ui;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.santosh.miniredditapp.BaseActivity;
import com.santosh.miniredditapp.MiniRedditApplication;
import com.santosh.miniredditapp.R;
import com.santosh.miniredditapp.adapter.RedditNewsAdapter;
import com.santosh.miniredditapp.data.RedditNewResponse;
import com.santosh.miniredditapp.databinding.ActivityRedditMainBinding;
import com.santosh.miniredditapp.model.ILoadmoreRedditItemsModel;
import com.santosh.miniredditapp.network.RedditAPIService;
import com.santosh.miniredditapp.viewmodel.RedditMainActivityView;
import com.santosh.miniredditapp.viewmodel.RedditMainActivityViewModel;

import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;

public class RedditMainActivity extends BaseActivity<ActivityRedditMainBinding,RedditMainActivityViewModel> implements CallNextPageListener, RedditMainActivityView {

    private RedditNewResponse redditNewResponse;
    private CompositeDisposable mainActivityCompositeDisposable;
    private String REDDIT_NEWS_KEY = "REDDIT_NEWS_KEY";
    private String PAGE_LIMIT = "10";

    RedditNewsAdapter redditNewsAdapter;
    LinearLayoutManager layoutManager;

    @Inject
    RedditAPIService redditAPIService;

    @Inject
    ILoadmoreRedditItemsModel loadmoreRedditItemsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MiniRedditApplication) getApplication()).getAppComponent().inject(this);
        mainActivityCompositeDisposable = new CompositeDisposable();

        viewModel = new RedditMainActivityViewModel(redditAPIService,loadmoreRedditItemsModel);
        viewModel.attach(this);

        bindView(R.layout.activity_reddit_main);
        binding.setIsLoading(true);

        redditNewsAdapter = new RedditNewsAdapter(this);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        binding.redditNewsRecyclerView.setLayoutManager(layoutManager);
        binding.redditNewsRecyclerView.setAdapter(redditNewsAdapter);

        if(savedInstanceState != null && savedInstanceState.containsKey(REDDIT_NEWS_KEY) ){
            redditNewResponse = (RedditNewResponse) savedInstanceState.getSerializable(REDDIT_NEWS_KEY);
            load(redditNewResponse);
        }else{
            viewModel.fetchRedditNewsFirstPage();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mainActivityCompositeDisposable != null && !mainActivityCompositeDisposable.isDisposed()) {
            mainActivityCompositeDisposable.dispose();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(redditNewResponse != null){
            outState.putSerializable(REDDIT_NEWS_KEY,redditNewResponse);
        }
    }


    @Override
    public void callNextPage(RedditNewResponse redditNewResponse) {
        binding.setIsLoading(true);
        viewModel.fetchRedditNewsSubsequentPages(loadmoreRedditItemsModel.getMoreRedditItems().getRedditResponseData().getAfter(),PAGE_LIMIT);
    }

    @Override
    public void load(RedditNewResponse newsItems) {
        redditNewResponse = newsItems;
        binding.setIsLoading(false);
        redditNewsAdapter.setRedditNewResponse(newsItems.getRedditResponseData().getRedditChildrenResponseList());

        binding.redditNewsRecyclerView.setHasFixedSize(true);
        binding.redditNewsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.redditNewsRecyclerView.setHasFixedSize(true);
        binding.redditNewsRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(newsItems,layoutManager,this));

    }

    @Override
    public void loadMore() {
        if(loadmoreRedditItemsModel != null){
            binding.setIsLoading(false);
            redditNewResponse = loadmoreRedditItemsModel.getMoreRedditItems();
            redditNewsAdapter.setRedditNewResponse(loadmoreRedditItemsModel.getMoreRedditItems().getRedditResponseData().getRedditChildrenResponseList());
        }
    }


}
