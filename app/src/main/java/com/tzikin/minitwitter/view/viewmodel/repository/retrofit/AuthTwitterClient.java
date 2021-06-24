package com.tzikin.minitwitter.view.viewmodel.repository.retrofit;

import androidx.lifecycle.MutableLiveData;

import com.tzikin.minitwitter.view.viewmodel.repository.model.entity.Tweet;
import com.tzikin.minitwitter.view.viewmodel.repository.retrofit.api.AuthTweetApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AuthTwitterClient {

    private static AuthTwitterClient instance = null;
    private AuthTweetApi api;

    public static AuthTwitterClient getInstance(){
        if(instance == null){
            instance = new AuthTwitterClient();
        }
        return instance;
    }

    private AuthTwitterClient(){

        api = RetrofitService.createAuthService(AuthTweetApi.class);
    }

    public AuthTweetApi getMiniTwitterService() {
        return api;
    }

    public MutableLiveData<List<Tweet>> getAllTweets() {
        MutableLiveData<List<Tweet>> routeData = new MutableLiveData<>();
        api.getAllTweets().enqueue(new Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                if (response.body() != null) {
                    routeData.setValue(response.body());
                } else {
                    routeData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable t) {
                routeData.setValue(null);
            }
        });

        return routeData;
    }

}
