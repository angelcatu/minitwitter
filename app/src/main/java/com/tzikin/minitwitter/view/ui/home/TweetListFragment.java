package com.tzikin.minitwitter.view.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.tzikin.minitwitter.R;
import com.tzikin.minitwitter.databinding.FragmentHomeBinding;

import com.tzikin.minitwitter.view.common.Constants;
import com.tzikin.minitwitter.view.ui.home.adapter.TweetAdapterJ;
import com.tzikin.minitwitter.view.viewmodel.repository.model.entity.Tweet;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TweetListFragment extends Fragment implements View.OnClickListener {

    private int tweetListType = 1;

    private TweetViewModel tweetViewModel;
    private FragmentHomeBinding binding;
    private List<Tweet> tweetList;
    private TweetAdapterJ tweetAdapter;

    public static TweetListFragment newInstance(int tweetListType){
        TweetListFragment fragment = new TweetListFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.TWEET_LIST_TYPE, tweetListType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            tweetListType = getArguments().getInt(Constants.TWEET_LIST_TYPE);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tweetViewModel = new ViewModelProvider(this).get(TweetViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindElements();
    }

    private void bindElements() {

        if(tweetListType == Constants.TWEET_LIST_ALL){
            binding.fabNewTweet.show();
        }else{
            binding.fabNewTweet.hide();
        }


        tweetAdapter = new TweetAdapterJ(requireActivity(), tweetList, tweet -> {
            int id = tweet.getId();
            tweetViewModel.likeTweet(id);
        });
        binding.recyclerTweet.setLayoutManager(new LinearLayoutManager(requireActivity()));
        binding.recyclerTweet.setHasFixedSize(true);
        binding.recyclerTweet.setAdapter(tweetAdapter);

        if(tweetListType == Constants.TWEET_LIST_ALL){
            loadTweets();
        }else if(tweetListType == Constants.TWEET_LIST_FAVS){
            loadFavTweetData();
        }

        binding.fabNewTweet.setOnClickListener(this);

        loadTweets();

        // Swipe-refresh
        binding.swipeRefreshTweets.setColorSchemeColors(getResources().getColor(R.color.bluecolor, null));
        binding.swipeRefreshTweets.setOnRefreshListener(() -> {
            binding.swipeRefreshTweets.setRefreshing(true);

            if(tweetListType == Constants.TWEET_LIST_ALL){
                loadNewTweets();
            }else if(tweetListType == Constants.TWEET_LIST_FAVS){
                loadNewFavTweetData();
            }
        });
    }

    private void loadNewFavTweetData() {
        tweetViewModel.getFavNewTweets().observe(requireActivity(), new Observer<List<Tweet>>() {
            @Override
            public void onChanged(List<Tweet> tweets) {
                tweetList = tweets;
                binding.swipeRefreshTweets.setRefreshing(false);
                tweetAdapter.setData(tweets);
                tweetViewModel.getFavNewTweets().removeObserver(this);
            }
        });
    }

    private void loadFavTweetData() {
        tweetViewModel.getFavTweets().observe(requireActivity(), tweets -> {
            tweetList = tweets;
            tweetAdapter.setData(tweetList);
        });
    }

    private void loadTweets() {

        tweetViewModel.getAllTweets().observe(requireActivity(), tweets -> {
            tweetList = tweets;
            tweetAdapter.setData(tweets);
        });
    }

    private void loadNewTweets(){
        tweetViewModel.getAllNewTweets().observe(requireActivity(), new Observer<List<Tweet>>() {
            @Override
            public void onChanged(List<Tweet> tweets) {
                tweetList = tweets;
                tweetAdapter.setData(tweets);
                binding.swipeRefreshTweets.setRefreshing(false);
                tweetViewModel.getAllNewTweets().removeObserver(this);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.fabNewTweet:
                NewTweetDialogFragment dialogTweet = new NewTweetDialogFragment();
                dialogTweet.show(requireActivity().getSupportFragmentManager(),"NewTweetDialogFragment");
                break;
        }
    }
}