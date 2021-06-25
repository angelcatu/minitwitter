package com.tzikin.minitwitter.view.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.tzikin.minitwitter.R;
import com.tzikin.minitwitter.databinding.NewTweetDialogBinding;
import com.tzikin.minitwitter.view.viewmodel.repository.repositories.TweetRepository;

import org.jetbrains.annotations.NotNull;

public class NewTweetDialogFragment extends DialogFragment implements View.OnClickListener {

    private NewTweetDialogBinding binding;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.full_screen_dialog_style);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = NewTweetDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindUIElements();
    }

    private void bindUIElements() {
        binding.btnTweet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnTweet:
                String message =  binding.edtTweetMessage.getText().toString();
                TweetRepository tweetRepository = new TweetRepository();
                tweetRepository.postNewTweet(message);
                tweetRepository.getPostNewTweet().observe(requireActivity(), response -> {
                    if(response != null){

                    }
                });
                break;
        }
    }
}
