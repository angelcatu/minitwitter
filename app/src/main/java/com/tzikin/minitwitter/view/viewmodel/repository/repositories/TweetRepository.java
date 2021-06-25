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
    private MutableLiveData<Tweet> likeTweet;

    public void getAllTweets(){
        allTweetsResponse = instance.getAllTweets();
    }

    public void postNewTweet(String message){
        NewTweet newTweet = new NewTweet(message);
        instance.postNewTweet(newTweet);
    }

    public void likeTweet(int idTweet){
        instance.likeTweet(idTweet);
    }

    public MutableLiveData<List<Tweet>> getAllTweetsResponse() {
        return allTweetsResponse;
    }

    public MutableLiveData<Tweet> getPostNewTweet() {
        return postNewTweet;
    }

    public MutableLiveData<Tweet> getLikeTweet() {
        return likeTweet;
    }
}
