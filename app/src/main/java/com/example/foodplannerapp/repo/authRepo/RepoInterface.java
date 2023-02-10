package com.example.foodplannerapp.repo.authRepo;

public interface RepoInterface {

    void signUp(String email , String pass);

    void login(String email , String pass);

    void logout();


}
