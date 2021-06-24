package com.tzikin.minitwitter.view.viewmodel.repository.repositories;

import androidx.lifecycle.MutableLiveData;

import com.tzikin.minitwitter.view.viewmodel.repository.model.entity.Tweet;
import com.tzikin.minitwitter.view.viewmodel.repository.retrofit.AuthTwitterClient;
import com.tzikin.minitwitter.view.viewmodel.repository.retrofit.MiniTwitterClient;
import com.tzikin.minitwitter.view.viewmodel.repository.retrofit.api.AuthTweetApi;

import java.util.List;


public class TweetRepository {

    AuthTwitterClient instance;
    AuthTweetApi service;

    public TweetRepository(){
        instance = AuthTwitterClient.getInstance();
        service = instance.getMiniTwitterService();
    }

    private MutableLiveData<List<Tweet>> allTweetsResponse;

    public void getAllTweets(){
        allTweetsResponse = instance.getAllTweets();
    }

    public MutableLiveData<List<Tweet>> getAllTweetsResponse() {
        return allTweetsResponse;
    }
}
