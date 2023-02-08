package com.example.foodplannerapp.models;

public interface RepoInterface {

    void signUp(String email , String pass);

    void login(String email , String pass);

    void logout();

}
