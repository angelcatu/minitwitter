package com.tzikin.minitwitter.view.ui.home.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.tzikin.minitwitter.view.viewmodel.repository.model.entity.Tweet;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TweetAdapterJ extends RecyclerView.Adapter<TweetAdapterJ.ViewHolder> {
    public TweetAdapterJ(FragmentActivity requireActivity, List<Tweet> tweetList) {
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
}
