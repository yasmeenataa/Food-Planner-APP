package com.example.foodplannerapp.models;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPref {

    private static Context appContext;
    private static final String SHARED_PREFERENCES_NAME = "user data";
    private static final String USER_NAME_KEY = "user name";
    private static final String USER_EMAIL_KEY = "user email";
    private static final String USER_PASSWORD_KEY = "user password";

    private static final String USER_ID_KEY = "user id";

    private MySharedPref() {

    }

    public static void init(Context context) {
        appContext = context;
    }

    public static SharedPreferences getSharedPreferences() {
        return appContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static void setUserName(String userName) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(USER_NAME_KEY, userName).apply();
    }

    public static String getUserName() {
        return getSharedPreferences().getString(USER_NAME_KEY, "");
    }

    public static void setUserEmail(String userEmail) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(USER_EMAIL_KEY, userEmail).apply();
    }

    public static String getUserEmail() {
        return getSharedPreferences().getString(USER_EMAIL_KEY, "");
    }

    public static void setUserPassword(String userPassword) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(USER_PASSWORD_KEY, userPassword).apply();
    }

    public static String getUserPassword() {
        return getSharedPreferences().getString(USER_PASSWORD_KEY, "");
    }

    public static void setUserId(String userEmail) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(USER_ID_KEY, userEmail).apply();
    }

    public static String getUserId() {
        return getSharedPreferences().getString(USER_ID_KEY, "");
    }


}
