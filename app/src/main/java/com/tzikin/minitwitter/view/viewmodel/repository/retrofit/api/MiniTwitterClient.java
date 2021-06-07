package com.tzikin.minitwitter.view.viewmodel.repository.retrofit.api;

import androidx.lifecycle.MutableLiveData;

import com.tzikin.minitwitter.view.viewmodel.repository.model.request.LoginRequest;
import com.tzikin.minitwitter.view.viewmodel.repository.model.request.SignUpRequest;
import com.tzikin.minitwitter.view.viewmodel.repository.model.response.LoginSignUpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MiniTwitterClient {

    private static MiniTwitterClient instance = null;
    private LoginRegisterApi api;

    public static MiniTwitterClient getInstance(){
        if(instance == null){
            instance = new MiniTwitterClient();
        }
        return instance;
    }

    private MiniTwitterClient(){
        api = RetrofitService.createService(LoginRegisterApi.class);
    }

    public LoginRegisterApi getMiniTwitterService() {
        return api;
    }

    public MutableLiveData<LoginSignUpResponse> doLogIn(LoginRequest request){
        MutableLiveData<LoginSignUpResponse> routeData = new MutableLiveData<>();

        api.doLogin(request).enqueue(new Callback<LoginSignUpResponse>() {
            @Override
            public void onResponse(Call<LoginSignUpResponse> call, Response<LoginSignUpResponse> response) {
                if(response.body() != null){
                    routeData.setValue(response.body());
                }else{
                    routeData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<LoginSignUpResponse> call, Throwable t) {
                routeData.setValue(null);
            }
        });

        return routeData;
    }

    public MutableLiveData<LoginSignUpResponse> doSignUp(SignUpRequest request){
        MutableLiveData<LoginSignUpResponse> routeData = new MutableLiveData<>();

        api.doSignUp(request).enqueue(new Callback<LoginSignUpResponse>() {
            @Override
            public void onResponse(Call<LoginSignUpResponse> call, Response<LoginSignUpResponse> response) {
                if(response.body() != null){
                    routeData.setValue(response.body());
                }else{
                    routeData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<LoginSignUpResponse> call, Throwable t) {
                routeData.setValue(null);
            }
        });

        return routeData;
    }

}
