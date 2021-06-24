package com.tzikin.minitwitter.view.viewmodel.repository.repositories;

import androidx.lifecycle.MutableLiveData;

import com.tzikin.minitwitter.view.viewmodel.repository.model.request.LoginRequest;
import com.tzikin.minitwitter.view.viewmodel.repository.model.request.SignUpRequest;
import com.tzikin.minitwitter.view.viewmodel.repository.model.response.LoginSignUpResponse;
import com.tzikin.minitwitter.view.viewmodel.repository.retrofit.MiniTwitterClient;
import com.tzikin.minitwitter.view.viewmodel.repository.retrofit.api.LoginRegisterApi;


public class LoginSignUpRepository {

    MiniTwitterClient instance ;
    LoginRegisterApi service;

    public LoginSignUpRepository(){
        instance = MiniTwitterClient.getInstance();
        service = instance.getMiniTwitterService();
    }

    // MutableLiveDatas
    private MutableLiveData<LoginSignUpResponse> loginSignUpResponse;
    private MutableLiveData<LoginSignUpResponse> singUpResponse;


    public void doLoginRequest(String email, String password){
        LoginRequest request = new LoginRequest(email, password);
        loginSignUpResponse = instance.doLogIn(request);
    }

    public void doSignUpRequest(String email, String username, String password){
        SignUpRequest request = new SignUpRequest(username, email, password);
        singUpResponse = instance.doSignUp(request);
    }

    public MutableLiveData<LoginSignUpResponse> getLoginSignUpResponse() {
        return loginSignUpResponse;
    }

    public MutableLiveData<LoginSignUpResponse> getSingUpResponse() {
        return singUpResponse;
    }
}
