package com.example.foodplannerapp.models;

import com.example.foodplannerapp.nework.NetworkDelegate;

public interface RepoInterface {

    void signUp(String email , String pass);

    void login(String email , String pass);

    void logout();
    void getAllIngredients(NetworkDelegate networkDelegate);
    void getAllAreas(NetworkDelegate networkDelegate);


}
