package com.tzikin.minitwitter.view.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.tzikin.minitwitter.view.viewmodel.repository.common.MyApp;

public class SharedPreferenceManager {

    private static final String APP_SETTINGS_FILE = "app_settings";

    private static SharedPreferences getSharedPreference(){
        return MyApp.getContext().getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE);
    }

    public SharedPreferenceManager() { }

    public static void setSomeStringValue(String dataLabel, String dataValue){
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putString(dataLabel, dataValue);
        editor.commit();
    }

    public static void setSomeBooleanValue(String dataLabel, boolean dataValue){
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putBoolean(dataLabel, dataValue);
        editor.commit();
    }

    public static String getSomeStringValue(String dataLabel){
        return getSharedPreference().getString(dataLabel, null);
    }

    public static boolean getSomeBooleanValue(String dataLabel){
        return getSharedPreference().getBoolean(dataLabel, false);
    }


}
