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


import com.tzikin.minitwitter.databinding.FragmentHomeBinding;

import com.tzikin.minitwitter.view.ui.home.adapter.TweetAdapterJ;
import com.tzikin.minitwitter.view.viewmodel.repository.model.entity.Tweet;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private List<Tweet> tweetList;
    private TweetAdapterJ tweetAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindElements();
    }

    private void bindElements() {

        tweetAdapter = new TweetAdapterJ(requireActivity(), tweetList);
        binding.recyclerTweet.setLayoutManager(new LinearLayoutManager(requireActivity()));
        binding.recyclerTweet.setHasFixedSize(true);
        binding.recyclerTweet.setAdapter(tweetAdapter);

        loadData();
    }

    private void loadData() {

        homeViewModel.getAllTweets().observe(requireActivity(), tweets -> {
            tweetList = tweets;
            tweetAdapter.setData(tweets);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}