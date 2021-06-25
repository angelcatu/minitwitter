package com.tzikin.minitwitter.view.viewmodel.repository.retrofit.api;

import com.tzikin.minitwitter.view.viewmodel.repository.model.entity.Tweet;
import com.tzikin.minitwitter.view.viewmodel.repository.model.request.NewTweet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AuthTweetApi {

    @GET("tweets/all")
    Call<List<Tweet>> getAllTweets();

    @POST("tweets/create")
    Call<Tweet> postMessage(@Body NewTweet request);

    @POST("tweets/like/{id}")
    Call<Tweet> likeTweet(@Path("id") int idTweet);
}
