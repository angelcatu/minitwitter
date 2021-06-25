package com.tzikin.minitwitter.view.ui.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.tzikin.minitwitter.R;
import com.tzikin.minitwitter.databinding.NewTweetDialogBinding;
import com.tzikin.minitwitter.view.common.Constants;
import com.tzikin.minitwitter.view.common.SharedPreferenceManager;
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

        // Update profile image
        String photoURL = SharedPreferenceManager.getSomeStringValue(Constants.PREF_PHOTO_URL);
        if (!photoURL.isEmpty())
            Glide.with(requireActivity()).load(Constants.PHOTO_URL_SERVER + photoURL).into(binding.imgProfileTweet);
    }

    private void bindUIElements() {
        binding.btnTweet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String message = binding.edtTweetMessage.getText().toString();
        switch (id) {
            case R.id.btnTweet:

                if (!message.isEmpty()) {
                    HomeViewModel tweetViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
                    tweetViewModel.insertTweet(message);
                } else {
                    Toast.makeText(requireActivity(), "Escribir un mensaje para publicar", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.imgClose:
                if (!message.isEmpty()) {
                    showDialogConfirm();
                } else {
                    getDialog().dismiss();
                }

                break;


        }
    }

    private void showDialogConfirm() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setMessage(requireActivity().getString(R.string.close_dialog_new_tweet));
        builder.setTitle(requireActivity().getString(R.string.cancel_new_tweet));

        builder.setPositiveButton(requireActivity().getString(R.string.eliminate), (dialog, which) -> {
            dialog.dismiss();
            getDialog().dismiss();
        }).setNegativeButton(requireActivity().getString(R.string.cancel), (dialog, which) -> {
            dialog.dismiss();
            getDialog().dismiss();
        }).show();

        AlertDialog alertDialog = builder.create();
    }
}
