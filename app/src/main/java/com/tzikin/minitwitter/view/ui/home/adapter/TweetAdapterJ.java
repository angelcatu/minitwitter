package com.tzikin.minitwitter.view.ui.home.adapter;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tzikin.minitwitter.R;
import com.tzikin.minitwitter.databinding.TweetLayoutBinding;
import com.tzikin.minitwitter.view.common.Constants;
import com.tzikin.minitwitter.view.common.SharedPreferenceManager;
import com.tzikin.minitwitter.view.viewmodel.repository.model.entity.Like;
import com.tzikin.minitwitter.view.viewmodel.repository.model.entity.Tweet;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TweetAdapterJ extends RecyclerView.Adapter<TweetAdapterJ.ViewHolder> {

    private Activity activity;
    private List<Tweet> tweetList;
    private String username;
    private OnLikeListener onLike;

    public TweetAdapterJ(FragmentActivity requireActivity, List<Tweet> tweetList, OnLikeListener onLike) {
        this.activity = requireActivity;
        this.tweetList = tweetList;
        this.username = SharedPreferenceManager.getSomeStringValue(Constants.PREF_USERNAME);
        this.onLike = onLike;

    }

    public void setData(List<Tweet> newData){
        this.tweetList = newData;
        notifyDataSetChanged(); // Refresh
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        TweetLayoutBinding view = TweetLayoutBinding.inflate(LayoutInflater.from(activity), parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        if(tweetList != null){
            holder.bind(tweetList.get(position), activity, username, onLike);
        }
    }

    @Override
    public int getItemCount() {
        if(tweetList != null){
            return tweetList.size();
        }else{
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TweetLayoutBinding binding;
        public ViewHolder(TweetLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Tweet tweet, Activity activity, String username, OnLikeListener onLike) {
            binding.txtUsername.setText(tweet.getUser().getUsername());
            binding.txtMessage.setText(tweet.getMensaje());
            binding.txtLike.setText(String.valueOf(tweet.getLikes().size()));

            if(!tweet.getUser().getPhotoUrl().isEmpty()){
                Glide.with(activity).load(tweet.getUser().getPhotoUrl()).into(binding.imgProfile);
            }

            Glide.with(activity).load(R.drawable.ic_without_like).into(binding.imgLike);
            binding.txtLike.setTextColor(activity.getColor(R.color.black));
            binding.txtLike.setTypeface(null, Typeface.NORMAL);

            for (Like like: tweet.getLikes()) {
                if(like.getUsername() == username){
                    Glide.with(activity).load(R.drawable.ic_with_like_black).into(binding.imgLike);
                    binding.txtLike.setTextColor(activity.getColor(R.color.pink));
                    binding.txtLike.setTypeface(null, Typeface.BOLD);
                }
            }

            binding.imgLike.setOnClickListener(v -> {
                onLike.onLikeTouchListener(tweet);
            });
        }
    }

    public interface OnLikeListener{
        void onLikeTouchListener(Tweet tweet);
    }
}
