package com.santosh.miniredditapp.viewmodel;

import com.santosh.miniredditapp.data.RedditChildrenResponseData;
import com.santosh.miniredditapp.data.RedditNewsResponse;
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

    /**
     * With the @Inject annotation on the constructor,
     * we instruct Dagger that an object of this class can be injected into other objects.
     * Dagger automatically calls this constructor, if an instance of this class is requested.
     * @param redditAPIService
     * @param loadmoreRedditItemsModel
     */
    @Inject
    public RedditMainActivityViewModel(RedditAPIService redditAPIService, ILoadmoreRedditItemsModel loadmoreRedditItemsModel) {
        this.redditAPIService = redditAPIService;
        this.loadmoreRedditItemsModel = loadmoreRedditItemsModel;
    }

    /**
     * This method is invoked at first launch, fetches data from the first page with page limit 10.
     * An empty "after" is passed as the query param.
     */
    public void fetchRedditNewsFirstPage(){
        compositeDisposable.add(redditAPIService.makeRedditNewsCall(EMPTY_AFTER, PAGE_LIMIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(redditNewResponse ->
                                loadMoreNewsItems(redditNewResponse,EMPTY_AFTER),
                        throwable ->view.error()));
    }

    /**
     * This method is called to fetch data for the susequent pages.
     * As user scroll through the app, this call is invoked.
     * @param after
     * @param limit
     */

    public void fetchRedditNewsSubsequentPages(String after, String limit){
        compositeDisposable.add(redditAPIService.makeRedditNewsCall(after, limit)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(redditNewResponse ->
                                                loadMoreNewsItems(redditNewResponse,redditNewResponse.getRedditResponseData().getAfter()),
                                        throwable ->view.error()));
    }

    /**
     * This method is repsonsible for saving new items to model class (LoadMoreRedditItemsModel)
     * @param responseList
     * @param after
     */

    public void loadMoreNewsItems(RedditNewsResponse responseList, String after){

        if(after.isEmpty()){
            loadmoreRedditItemsModel.setMoreRedditItems(responseList);
            view.load(responseList);
        }else if(responseList.getRedditResponseData().getRedditChildrenResponseList().size() > 0){
            RedditNewsResponse response = loadmoreRedditItemsModel.getMoreRedditItems();
            List<RedditChildrenResponseData> redditChildrenResponseDataList = response.getRedditResponseData().getRedditChildrenResponseList();
            redditChildrenResponseDataList.addAll(responseList.getRedditResponseData().getRedditChildrenResponseList());
            loadmoreRedditItemsModel.setMoreRedditItems(response);
            response.getRedditResponseData().setAfter(after);
            view.loadMore();
        }
    }

}
