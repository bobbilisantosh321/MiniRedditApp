package com.santosh.miniredditapp.viewmodel;

import com.santosh.miniredditapp.data.RedditChildrenResponseData;
import com.santosh.miniredditapp.data.RedditNewResponse;
import com.santosh.miniredditapp.model.ILoadmoreRedditItemsModel;
import com.santosh.miniredditapp.network.RedditAPIService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RedditMainActivityViewModel extends BaseViewModel<RedditMainActivityView> {

    private String PAGE_LIMIT = "10";
    private String EMPTY_AFTER = "";

    public RedditAPIService redditAPIService;

    @Inject
    public ILoadmoreRedditItemsModel loadmoreRedditItemsModel;

    @Inject
    public RedditMainActivityViewModel(RedditAPIService redditAPIService, ILoadmoreRedditItemsModel loadmoreRedditItemsModel) {
        this.redditAPIService = redditAPIService;
        this.loadmoreRedditItemsModel = loadmoreRedditItemsModel;
    }

    public void fetchRedditNewsFirstPage(){
        compositeDisposable.add(redditAPIService.makeRedditNewsCall(EMPTY_AFTER, PAGE_LIMIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(redditNewResponse ->
                                loadMoreNewsItems(redditNewResponse,EMPTY_AFTER),
                        throwable ->view.error()));
    }

    public void fetchRedditNewsSubsequentPages(String after, String limit){
        compositeDisposable.add(redditAPIService.makeRedditNewsCall(after, limit)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(redditNewResponse ->
                                                loadMoreNewsItems(redditNewResponse,redditNewResponse.getRedditResponseData().getAfter()),
                                        throwable ->view.error()));
    }

    public void loadMoreNewsItems(RedditNewResponse responseList, String after){

        if(after.isEmpty()){
            loadmoreRedditItemsModel.setMoreRedditItems(responseList);
            view.load(responseList);
        }else if(responseList.getRedditResponseData().getRedditChildrenResponseList().size() > 0){
            RedditNewResponse response = loadmoreRedditItemsModel.getMoreRedditItems();
            List<RedditChildrenResponseData> redditChildrenResponseDataList = response.getRedditResponseData().getRedditChildrenResponseList();
            redditChildrenResponseDataList.addAll(responseList.getRedditResponseData().getRedditChildrenResponseList());
            loadmoreRedditItemsModel.setMoreRedditItems(response);
            response.getRedditResponseData().setAfter(after);
            view.loadMore();
        }
    }

}
