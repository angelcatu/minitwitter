package com.tzikin.minitwitter.view.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tzikin.minitwitter.view.viewmodel.repository.model.entity.Tweet;
import com.tzikin.minitwitter.view.viewmodel.repository.repositories.TweetRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<Tweet>> allTweets = new MutableLiveData<>();

    public HomeViewModel() {
    }

    public MutableLiveData<List<Tweet>> getAllTweets() {
        return allTweets;
    }

    public void setAllTweets(MutableLiveData<List<Tweet>> allTweets) {
        this.allTweets = allTweets;
    }
}