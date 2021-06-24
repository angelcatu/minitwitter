package com.tzikin.minitwitter.view.viewmodel.repository.retrofit.api;

import com.tzikin.minitwitter.view.viewmodel.repository.model.entity.Tweet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AuthTweetApi {

    @GET("tweets/all")
    Call<List<Tweet>> getAllTweets();
}
