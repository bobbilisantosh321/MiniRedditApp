package com.santosh.miniredditapp.network;


import com.santosh.miniredditapp.data.RedditNewsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RedditAPIService {

    /**
     * This method is responsible for making HTTP request with query params "after" and "limit"
     * @param after
     * @param limit
     * @return
     */
    @GET("/top.json")
    Single<RedditNewsResponse> makeRedditNewsCall(@Query("after") String after,
                                                  @Query("limit") String limit);

}

