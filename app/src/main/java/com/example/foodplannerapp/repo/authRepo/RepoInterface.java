package com.example.foodplannerapp.repo.authRepo;

import com.example.foodplannerapp.nework.NetworkDelegate;

public interface RepoInterface {

    void signUp(String email , String pass);

    void login(String email , String pass);

    void logout();




}
