package com.tzikin.minitwitter.view.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tzikin.minitwitter.view.viewmodel.repository.model.entity.Tweet;
import com.tzikin.minitwitter.view.viewmodel.repository.repositories.TweetRepository;

import java.util.List;

public class TweetViewModel extends ViewModel {

    private MutableLiveData<List<Tweet>> allTweets;
    private MutableLiveData<List<Tweet>> favTweets;
    private TweetRepository tweetRepository;


    public TweetViewModel() {
        tweetRepository = new TweetRepository();
        allTweets = tweetRepository.getAllTweetsResponse();
    }

    public MutableLiveData<List<Tweet>> getAllTweets() {
        return allTweets;
    }

    public MutableLiveData<List<Tweet>> getAllNewTweets(){
        allTweets = tweetRepository.getAllTweetsResponse();
        return allTweets;
    }

    public MutableLiveData<List<Tweet>> getFavTweets(){
        favTweets = tweetRepository.favTweets();
        return favTweets;
    }

    public MutableLiveData<List<Tweet>> getFavNewTweets(){
        getFavTweets();
        return getFavTweets();
    }

    public void deleteTweet(int idTweet){
        tweetRepository.deleteTweet(idTweet);
    }

    public TweetRepository getTweetRepository() {
        return tweetRepository;
    }

    public void insertTweet(String message){
        tweetRepository.postNewTweet(message);
    }

    public void likeTweet(int idTweet){tweetRepository.likeTweet(idTweet);}
}