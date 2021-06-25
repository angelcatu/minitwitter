package com.tzikin.minitwitter.view.viewmodel.repository.repositories;

import androidx.lifecycle.MutableLiveData;

import com.tzikin.minitwitter.view.viewmodel.repository.model.entity.Tweet;
import com.tzikin.minitwitter.view.viewmodel.repository.model.request.NewTweet;
import com.tzikin.minitwitter.view.viewmodel.repository.retrofit.AuthTwitterClient;
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
    private MutableLiveData<Tweet> postNewTweet;

    public void getAllTweets(){
        allTweetsResponse = instance.getAllTweets();
    }

    public void postNewTweet(String message){
        NewTweet newTweet = new NewTweet(message);
        postNewTweet = instance.postNewTweet(newTweet);
    }

    public MutableLiveData<List<Tweet>> getAllTweetsResponse() {
        return allTweetsResponse;
    }

    public MutableLiveData<Tweet> getPostNewTweet() {
        return postNewTweet;
    }
}
