package com.tzikin.minitwitter.view.viewmodel.repository.repositories;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tzikin.minitwitter.view.common.Constants;
import com.tzikin.minitwitter.view.common.SharedPreferenceManager;
import com.tzikin.minitwitter.view.viewmodel.repository.common.MyApp;
import com.tzikin.minitwitter.view.viewmodel.repository.model.entity.Tweet;
import com.tzikin.minitwitter.view.viewmodel.repository.model.request.NewTweet;
import com.tzikin.minitwitter.view.viewmodel.repository.model.request.UpdateProfileRequest;
import com.tzikin.minitwitter.view.viewmodel.repository.model.response.ProfileResponse;
import com.tzikin.minitwitter.view.viewmodel.repository.model.response.UploadPhotoResponse;
import com.tzikin.minitwitter.view.viewmodel.repository.retrofit.AuthTwitterClient;
import com.tzikin.minitwitter.view.viewmodel.repository.retrofit.api.AuthTweetApi;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileRepository {

    AuthTwitterClient instance;
    AuthTweetApi service;
    private MutableLiveData<ProfileResponse> userProfile;
    private MutableLiveData<String> photoProfile;

    public ProfileRepository(){
        instance = AuthTwitterClient.getInstance();
        service = instance.getMiniTwitterService();
        userProfile = getProfile();
        photoProfile = new MutableLiveData<>();
    }

    public MutableLiveData<ProfileResponse> getProfile(){
        if(userProfile == null){
            userProfile = new MutableLiveData<>();
        }

        Call<ProfileResponse> call = service.getProfile();
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if(response.isSuccessful()){
                    userProfile.setValue(response.body());
                }else{
                    Toast.makeText(MyApp.getContext(), "Algo ha ido mal", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Toast.makeText(MyApp.getContext(), "Error en la conexión", Toast.LENGTH_LONG).show();
            }
        });

        return userProfile;
    }

    public void updateProfile(UpdateProfileRequest request){
        Call<ProfileResponse> call = service.updateProfile(request);
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if(response.isSuccessful()){
                    userProfile.setValue(response.body());
                }else{
                    Toast.makeText(MyApp.getContext(), "Algo ha ido mal", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Toast.makeText(MyApp.getContext(), "Error en la conexión", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void uploadPhoto(String photoPath){
        File file = new File(photoPath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
        Call<UploadPhotoResponse> call = service.uploadPhoto(requestBody);

        call.enqueue(new Callback<UploadPhotoResponse>() {
            @Override
            public void onResponse(Call<UploadPhotoResponse> call, Response<UploadPhotoResponse> response) {
                if(response.isSuccessful()){
                    if(response.body() != null)
                        SharedPreferenceManager.setSomeStringValue(Constants.PREF_PHOTO_URL, response.body().getFilename());
                        photoProfile.setValue(response.body().getFilename());
                }else{
                    Toast.makeText(MyApp.getContext(), "Algo ha ido mal", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UploadPhotoResponse> call, Throwable t) {
                Toast.makeText(MyApp.getContext(), "Error en la conexión", Toast.LENGTH_LONG).show();
            }
        });
    }

    public MutableLiveData<String> getPhotoProfile() {
        return photoProfile;
    }
}
