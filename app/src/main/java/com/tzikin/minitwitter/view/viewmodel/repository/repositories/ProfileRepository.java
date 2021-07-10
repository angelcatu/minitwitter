package com.tzikin.minitwitter.view.viewmodel.repository.repositories;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.tzikin.minitwitter.view.viewmodel.repository.common.MyApp;
import com.tzikin.minitwitter.view.viewmodel.repository.model.entity.Tweet;
import com.tzikin.minitwitter.view.viewmodel.repository.model.request.NewTweet;
import com.tzikin.minitwitter.view.viewmodel.repository.model.response.ProfileResponse;
import com.tzikin.minitwitter.view.viewmodel.repository.retrofit.AuthTwitterClient;
import com.tzikin.minitwitter.view.viewmodel.repository.retrofit.api.AuthTweetApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileRepository {

    AuthTwitterClient instance;
    AuthTweetApi service;
    private MutableLiveData<ProfileResponse> userProfile;

    public ProfileRepository(){
        instance = AuthTwitterClient.getInstance();
        service = instance.getMiniTwitterService();
        userProfile = getProfile();
    }

    private MutableLiveData<List<Tweet>> allTweetsResponse;
    private MutableLiveData<Tweet> postNewTweet;
    private MutableLiveData<Tweet> likeTweet;

    public void getAllTweets(){
        allTweetsResponse = instance.getAllTweets();
    }

    public void postNewTweet(String message){
        NewTweet newTweet = new NewTweet(message);
        instance.postNewTweet(newTweet);
    }

    public void likeTweet(int idTweet){
        instance.likeTweet(idTweet);
    }

    public MutableLiveData<List<Tweet>> favTweets(){
        return instance.getFavsTweets();
    }

    public void deleteTweet(int idTweet){
        instance.deleteTweet(idTweet);
    }

    public MutableLiveData<List<Tweet>> getAllTweetsResponse() {
        return allTweetsResponse;
    }

    public MutableLiveData<Tweet> getPostNewTweet() {
        return postNewTweet;
    }

    public MutableLiveData<Tweet> getLikeTweet() {
        return likeTweet;
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

        return null;
    }
}
