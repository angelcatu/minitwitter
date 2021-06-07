package com.tzikin.minitwitter.view.viewmodel.repository.retrofit.api;

import com.tzikin.minitwitter.view.viewmodel.repository.model.request.LoginRequest;
import com.tzikin.minitwitter.view.viewmodel.repository.model.request.SignUpRequest;
import com.tzikin.minitwitter.view.viewmodel.repository.model.response.LoginSignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginRegisterApi {

    @POST("auth/login")
    Call<LoginSignUpResponse> doLogin(@Body LoginRequest request);

    @POST("auth/signup")
    Call<LoginSignUpResponse> doSignUp(@Body SignUpRequest request);
}
