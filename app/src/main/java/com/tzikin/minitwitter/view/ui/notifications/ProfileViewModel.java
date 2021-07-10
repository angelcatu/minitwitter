package com.tzikin.minitwitter.view.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tzikin.minitwitter.view.viewmodel.repository.model.response.ProfileResponse;
import com.tzikin.minitwitter.view.viewmodel.repository.repositories.ProfileRepository;

public class ProfileViewModel extends ViewModel {

    private ProfileRepository profileRepository;
    public LiveData<ProfileResponse> userProfile;

    public ProfileViewModel() {
        profileRepository = new ProfileRepository();
        userProfile = profileRepository.getProfile();
    }
}