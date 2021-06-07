package com.tzikin.minitwitter.view.viewmodel.repository.repositories;

import androidx.lifecycle.MutableLiveData;

import com.tzikin.minitwitter.view.viewmodel.repository.model.request.LoginRequest;
import com.tzikin.minitwitter.view.viewmodel.repository.model.response.LoginSignUpResponse;
import com.tzikin.minitwitter.view.viewmodel.repository.retrofit.api.LoginRegisterApi;
import com.tzikin.minitwitter.view.viewmodel.repository.retrofit.api.MiniTwitterClient;

public class LoginSignUpRepository {

    MiniTwitterClient instance ;
    LoginRegisterApi service;

    public LoginSignUpRepository(){
        instance = MiniTwitterClient.getInstance();
        service = instance.getMiniTwitterService();
    }

    // MutableLiveDatas
    private MutableLiveData<LoginSignUpResponse> loginSignUpResponse;


    public void doLoginRequest(String email, String password){
        LoginRequest request = new LoginRequest(email, password);
        loginSignUpResponse = instance.doLogIn(request);
    }

    public void doLogin(LoginRequest request){
        service.doLogin(request);
    }

    public MiniTwitterClient getInstance() {
        return instance;
    }

    public void setInstance(MiniTwitterClient instance) {
        this.instance = instance;
    }

    public LoginRegisterApi getService() {
        return service;
    }

    public void setService(LoginRegisterApi service) {
        this.service = service;
    }

    public MutableLiveData<LoginSignUpResponse> getLoginSignUpResponse() {
        return loginSignUpResponse;
    }

    public void setLoginSignUpResponse(MutableLiveData<LoginSignUpResponse> loginSignUpResponse) {
        this.loginSignUpResponse = loginSignUpResponse;
    }
}
