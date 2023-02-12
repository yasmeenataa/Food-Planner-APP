package com.example.foodplannerapp.ui.profile;

import com.example.foodplannerapp.models.MySharedPref;
import com.example.foodplannerapp.repo.authRepo.Repo;
import com.example.foodplannerapp.repo.mealRepo.MealRepo;
import com.example.foodplannerapp.utils.FirebaseOnCompleteListener;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfilePresenter implements ProfilePresenterInterface, FirebaseOnCompleteListener {


    private MealRepo repo;
    private Repo auRepo;

    private ProfileViewInterface profileViewInterface;

    public ProfilePresenter(ProfileViewInterface profileViewInterface) {
        this.profileViewInterface = profileViewInterface;
        repo = MealRepo.getMealRepoInstance();
        auRepo = new Repo(this);

    }

    @Override
    public void deleteFavTableRoom() {
        repo.deleteFavTableRoom()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        profileViewInterface.onLogoutFail(e.getMessage());
                    }
                });
    }

    @Override
    public void deleteWeekTableRoom() {
        repo.deleteWeekTableRoom()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                });
    }

    @Override
    public void logoutFromApp() {
        deleteFavTableRoom();
        deleteWeekTableRoom();
        auRepo.logout();
    }

    @Override
    public void onSuccess(String userId) {

        profileViewInterface.onLogoutSuccess();
    }

    @Override
    public void onFail(String message) {
        profileViewInterface.onLogoutFail(message);
    }
}
