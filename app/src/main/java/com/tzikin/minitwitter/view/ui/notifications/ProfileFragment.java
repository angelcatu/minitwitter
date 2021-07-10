package com.tzikin.minitwitter.view.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.tzikin.minitwitter.R;
import com.tzikin.minitwitter.databinding.FragmentProfileBinding;
import com.tzikin.minitwitter.view.common.Constants;
import com.tzikin.minitwitter.view.viewmodel.repository.model.response.ProfileResponse;

import org.jetbrains.annotations.NotNull;


public class ProfileFragment extends Fragment implements View.OnClickListener {

    private ProfileViewModel profileViewModel;
    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnSaveProfile.setOnClickListener(this);
        binding.btnPasswordUpdate.setOnClickListener(this);


        //ViewModel
        profileViewModel.userProfile.observe(requireActivity(), response -> {
            binding.edtUsernameProfile.setText(response.getUsername());
            binding.edtEmailProfile.setText(response.getEmail());
            binding.edtWebsite.setText(response.getWebsite());
            if(!response.getPhotoURL().isEmpty()){
                Glide.with(requireActivity()).load(Constants.PHOTO_URL_SERVER + response.getPhotoURL()).into(binding.imgProfile);
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
            case R.id.btnSaveProfile:
                break;

            case R.id.btnPasswordUpdate:
                break;
        }
    }
}