package com.santosh.miniredditapp.network;


import com.santosh.miniredditapp.data.RedditResponseData;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RedditAPIService {

    @GET("/top.json")
    Single<List<RedditResponseData>> makeRedditNewsCall(@Query("after") String after,
                                                        @Query("limit") String limit);

}

