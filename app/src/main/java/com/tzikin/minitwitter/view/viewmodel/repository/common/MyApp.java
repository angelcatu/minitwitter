package com.tzikin.minitwitter.view.viewmodel.repository.common;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {

    private static MyApp instance;

    public static MyApp getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance;
    }
}
