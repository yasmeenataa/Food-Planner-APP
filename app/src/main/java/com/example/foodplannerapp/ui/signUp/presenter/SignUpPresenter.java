package com.example.foodplannerapp.ui.signUp.presenter;

import com.example.foodplannerapp.ui.signUp.view.ViewInterface;
import com.example.foodplannerapp.utils.FirebaseOnCompleteListener;
import com.example.foodplannerapp.repo.authRepo.Repo;
import com.example.foodplannerapp.repo.authRepo.RepoInterface;
import com.example.foodplannerapp.models.MySharedPref;

public class SignUpPresenter implements PresenterInterface, FirebaseOnCompleteListener {

    private ViewInterface viewInterface;
    private RepoInterface repoInterface;

    public SignUpPresenter(ViewInterface viewInterface) {
        this.viewInterface = viewInterface;
        this.repoInterface = new Repo(this);
    }

    @Override
    public void onSuccess(String userId) {
        MySharedPref.setUserId(userId);
        viewInterface.onRegisterSuccess(userId);
    }

    @Override
    public void onFail(String message) {
        viewInterface.onRegisterFail(message);

    }

    @Override
    public void signUp(String email, String pass) {
        repoInterface.signUp(email, pass);
    }

    @Override
    public void login(String email, String pass) {
        repoInterface.login(email, pass);
    }
}
