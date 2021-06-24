package com.tzikin.minitwitter.view.viewmodel.repository.retrofit;

import com.tzikin.minitwitter.view.common.Constants;
import com.tzikin.minitwitter.view.common.SharedPreferenceManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    public AuthInterceptor(){}

    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = SharedPreferenceManager.getSomeStringValue(Constants.PREF_TOKEN);
        Request request = chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer "+token)
                .build();

        return chain.proceed(request);
    }
}
