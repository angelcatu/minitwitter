package com.tzikin.minitwitter.view.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tzikin.minitwitter.view.viewmodel.repository.model.entity.Tweet;
import com.tzikin.minitwitter.view.viewmodel.repository.repositories.TweetRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<Tweet>> allTweets;
    private TweetRepository tweetRepository;


    public HomeViewModel() {
        tweetRepository = new TweetRepository();
        allTweets = tweetRepository.getAllTweetsResponse();
    }

    public MutableLiveData<List<Tweet>> getAllTweets() {
        return allTweets;
    }

    public TweetRepository getTweetRepository() {
        return tweetRepository;
    }

    public void insertTweet(String message){
        tweetRepository.postNewTweet(message);
    }
}