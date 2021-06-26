package com.tzikin.minitwitter.view.viewmodel.repository.retrofit;

import androidx.lifecycle.MutableLiveData;

import com.tzikin.minitwitter.view.common.Constants;
import com.tzikin.minitwitter.view.common.SharedPreferenceManager;
import com.tzikin.minitwitter.view.viewmodel.repository.model.entity.Like;
import com.tzikin.minitwitter.view.viewmodel.repository.model.entity.Tweet;
import com.tzikin.minitwitter.view.viewmodel.repository.model.request.NewTweet;
import com.tzikin.minitwitter.view.viewmodel.repository.retrofit.api.AuthTweetApi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AuthTwitterClient {

    private static AuthTwitterClient instance = null;
    private AuthTweetApi api;
    private MutableLiveData<List<Tweet>> allTweets;
    private MutableLiveData<List<Tweet>> favTweets;

    public static AuthTwitterClient getInstance(){
        if(instance == null){
            instance = new AuthTwitterClient();
        }
        return instance;
    }

    private AuthTwitterClient(){

        api = RetrofitService.createAuthService(AuthTweetApi.class);
    }

    public AuthTweetApi getMiniTwitterService() {
        return api;
    }

    public MutableLiveData<List<Tweet>> getAllTweets() {
        if(allTweets == null){
            allTweets = new MutableLiveData<>();
        }
        api.getAllTweets().enqueue(new Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                if (response.body() != null) {
                    allTweets.setValue(response.body());
                } else {
                    allTweets.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable t) {
                allTweets.setValue(null);
            }
        });

        return allTweets;
    }

    public MutableLiveData<List<Tweet>> getFavsTweets(){
        if(favTweets == null){
            favTweets = new MutableLiveData<>();
        }

        String username = SharedPreferenceManager.getSomeStringValue(Constants.PREF_USERNAME);

        List<Tweet> newFavList = new ArrayList<>();
        Iterator itTweets = allTweets.getValue().iterator();

        while(itTweets.hasNext()){
            Tweet current = (Tweet) itTweets.next();
            Iterator itLikes = current.getLikes().iterator();
            boolean finded = false;

            while(itLikes.hasNext() && !finded){
                Like like = (Like) itLikes.next();
                if(like.getUsername().equals(username)){
                    finded = true;
                    newFavList.add(current);
                }
            }
        }

        favTweets.setValue(newFavList);

        return favTweets;

    }

    public void postNewTweet(NewTweet request) {
        MutableLiveData<Tweet> routeData = new MutableLiveData<>();
        api.postMessage(request).enqueue(new Callback<Tweet>() {
            @Override
            public void onResponse(Call<Tweet> call, Response<Tweet> response) {
                if(response.body() != null){

                    List<Tweet> clonedList = new ArrayList<>();
                    clonedList.add(response.body());

                    for(int i = 0; i < allTweets.getValue().size(); i++){
                        clonedList.add(new Tweet(allTweets.getValue().get(i)));
                    }

                    allTweets.setValue(clonedList);

                    routeData.setValue(response.body());
                }else{
                    routeData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Tweet> call, Throwable t) {
                    routeData.setValue(null);
            }
        });
    }

    public void likeTweet(int idTweet){
        MutableLiveData<Tweet> routeData = new MutableLiveData<>();
        api.likeTweet(idTweet).enqueue(new Callback<Tweet>() {
            @Override
            public void onResponse(Call<Tweet> call, Response<Tweet> response) {
                if(response.body() != null){

                    List<Tweet> clonedList = new ArrayList<>();
                    clonedList.add(response.body());

                    for(int i = 0; i < allTweets.getValue().size(); i++){

                        if(allTweets.getValue().get(i).getId() == idTweet){
                            clonedList.add(response.body());
                        }else{
                            clonedList.add(new Tweet(allTweets.getValue().get(i)));
                        }
                    }

                    allTweets.setValue(clonedList);

                    getFavsTweets();

                }else{
                    routeData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Tweet> call, Throwable t) {
                routeData.setValue(null);
            }
        });
    }

}
