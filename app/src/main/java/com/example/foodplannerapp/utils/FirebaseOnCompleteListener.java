package com.example.foodplannerapp.utils;

public interface FirebaseOnCompleteListener {

    void onSuccess(String userId);

    void onFail(String message);
}
