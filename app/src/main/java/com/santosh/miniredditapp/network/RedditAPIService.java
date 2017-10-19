package com.santosh.miniredditapp.network;


import com.santosh.miniredditapp.data.RedditNewResponse;
import com.santosh.miniredditapp.data.RedditResponseData;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RedditAPIService {

    @GET("/top.json")
    Single<RedditNewResponse> makeRedditNewsCall(@Query("after") String after,
                                                     @Query("limit") String limit);

}

