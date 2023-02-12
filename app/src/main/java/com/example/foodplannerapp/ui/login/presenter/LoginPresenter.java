package com.example.foodplannerapp.ui.login.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.foodplannerapp.models.ModelMeal;
import com.example.foodplannerapp.models.WeekPlannerModel;
import com.example.foodplannerapp.repo.mealRepo.MealRepo;
import com.example.foodplannerapp.ui.login.ViewInterface;
import com.example.foodplannerapp.utils.Constants;
import com.example.foodplannerapp.utils.FirebaseOnCompleteListener;
import com.example.foodplannerapp.repo.authRepo.Repo;
import com.example.foodplannerapp.repo.authRepo.RepoInterface;
import com.example.foodplannerapp.models.MySharedPref;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginPresenter implements PresenterInterface, FirebaseOnCompleteListener {

    private RepoInterface repoInterface;
    private ViewInterface viewInterface;


    private MealRepo mealRepo;

    private DatabaseReference reference;

    public LoginPresenter(ViewInterface viewInterface) {
        this.viewInterface = viewInterface;
        this.repoInterface = new Repo(this);
        reference = FirebaseDatabase.getInstance().getReference();
        mealRepo = MealRepo.getMealRepoInstance();
    }

    @Override
    public void login(String email, String pass) {
        repoInterface.login(email, pass);

    }

    @Override
    public void getFavData() {
        reference.child(MySharedPref.getUserId())
                .child(Constants.FAV_REF)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChildren()) {
                            for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                ModelMeal modelMeal = snapshot1.getValue(ModelMeal.class);
                                observeInsertionDataToFav(modelMeal);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    @Override
    public void getPlanData() {
        reference.child(MySharedPref.getUserId())
                .child(Constants.WEEK_REF)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChildren()) {
                            for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                WeekPlannerModel weekPlannerModel = snapshot1.getValue(WeekPlannerModel.class);
                                observeInsertionDataToPlan(weekPlannerModel);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void observeInsertionDataToFav(ModelMeal modelMeal) {
        mealRepo.insertMeal(modelMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }
                });
    }

    private void observeInsertionDataToPlan(WeekPlannerModel weekPlannerModel) {
        mealRepo.insertWeekMeal(weekPlannerModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }
                });
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
