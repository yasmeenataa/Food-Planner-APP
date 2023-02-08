package com.example.foodplannerapp.models;

public interface FirebaseOnCompleteListener {

    void onSuccess(String userId);

    void onFail(String message);
}
