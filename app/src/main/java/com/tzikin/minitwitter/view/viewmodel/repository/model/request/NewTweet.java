package com.tzikin.minitwitter.view.viewmodel.repository.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewTweet {

    @SerializedName("mensaje")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NewTweet(String message) {
        this.message = message;
    }
}
