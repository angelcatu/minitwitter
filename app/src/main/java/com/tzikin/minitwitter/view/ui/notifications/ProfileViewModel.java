package com.tzikin.minitwitter.view.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tzikin.minitwitter.view.viewmodel.repository.model.request.UpdateProfileRequest;
import com.tzikin.minitwitter.view.viewmodel.repository.model.response.ProfileResponse;
import com.tzikin.minitwitter.view.viewmodel.repository.repositories.ProfileRepository;

public class ProfileViewModel extends ViewModel {

    private ProfileRepository profileRepository;
    public LiveData<ProfileResponse> userProfile;
    public LiveData<String> photoProfile;

    public ProfileViewModel() {
        profileRepository = new ProfileRepository();
        userProfile = profileRepository.getProfile();
        photoProfile = profileRepository.getPhotoProfile();
    }

    public void updateProfile(UpdateProfileRequest request){
        profileRepository.updateProfile(request);
    }

    public void uploadPhoto(String photoPath){
        profileRepository.uploadPhoto(photoPath);
    }
}