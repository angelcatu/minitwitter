package com.tzikin.minitwitter.view.viewmodel.repository.retrofit.api;

import com.tzikin.minitwitter.view.viewmodel.repository.model.entity.DeleteTweet;
import com.tzikin.minitwitter.view.viewmodel.repository.model.entity.Tweet;
import com.tzikin.minitwitter.view.viewmodel.repository.model.request.NewTweet;
import com.tzikin.minitwitter.view.viewmodel.repository.model.request.UpdateProfileRequest;
import com.tzikin.minitwitter.view.viewmodel.repository.model.response.ProfileResponse;
import com.tzikin.minitwitter.view.viewmodel.repository.model.response.UploadPhotoResponse;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface AuthTweetApi {

    //TWEETS
    @GET("tweets/all")
    Call<List<Tweet>> getAllTweets();

    @POST("tweets/create")
    Call<Tweet> postMessage(@Body NewTweet request);

    @POST("tweets/like/{id}")
    Call<Tweet> likeTweet(@Path("id") int idTweet);

    @GET("tweets/favs")
    Call<List<Tweet>> getFavTweets();

    @DELETE("tweets/{id}")
    Call<DeleteTweet> deleteTweet(@Path("id") int idTweet);



    //USERS
    @GET("users/profile")
    Call<ProfileResponse> getProfile();

    @PUT("users/profile")
    Call<ProfileResponse> updateProfile(@Body UpdateProfileRequest request);

    @POST("users/uploadprofilephoto")
    Call<UploadPhotoResponse> uploadPhoto(@Part("file\"; filename=photo.jpeg\" ")RequestBody file);



}
