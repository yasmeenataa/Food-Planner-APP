package com.example.foodplannerapp.ui.login.presenter;

import com.example.foodplannerapp.ui.login.ViewInterface;
import com.example.foodplannerapp.utils.FirebaseOnCompleteListener;
import com.example.foodplannerapp.repo.authRepo.Repo;
import com.example.foodplannerapp.repo.authRepo.RepoInterface;
import com.example.foodplannerapp.models.MySharedPref;

public class LoginPresenter implements PresenterInterface, FirebaseOnCompleteListener {

    private RepoInterface repoInterface;
    private ViewInterface viewInterface;

    public LoginPresenter(ViewInterface viewInterface) {
        this.viewInterface = viewInterface;
        this.repoInterface = new Repo(this);
    }

    @Override
    public void login(String email, String pass) {
        repoInterface.login(email, pass);

    }

    @Override
    public void onSuccess(String userId) {
        MySharedPref.setUserId(userId);
        viewInterface.onLoginSuccess(userId);
    }

    @Override
    public void onFail(String message) {
        viewInterface.onLoginFail(message);
    }
}
