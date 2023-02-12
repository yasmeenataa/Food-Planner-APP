package com.example.foodplannerapp.ui.detailsFragment.presenter;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.foodplannerapp.models.ModelMeal;
import com.example.foodplannerapp.models.MySharedPref;
import com.example.foodplannerapp.repo.mealRepo.MealRepo;
import com.example.foodplannerapp.ui.detailsFragment.view.DetailsViewInterface;
import com.example.foodplannerapp.utils.Constants;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetailsPresenter {


    private DetailsViewInterface viewInterface;

    private MealRepo mealRepo;

    private DatabaseReference ref;

    boolean isFav;


    public DetailsPresenter(DetailsViewInterface viewInterface) {
        this.viewInterface = viewInterface;
        mealRepo = MealRepo.getMealRepoInstance();
        ref = FirebaseDatabase.getInstance().getReference();
        isFav = false;
    }


    public void getData(String mealId, LifecycleOwner lifecycleOwner) {
        mealRepo.getMealById(mealId);
        mealRepo.getListLiveData().observe(lifecycleOwner, new Observer<ArrayList<ModelMeal>>() {
            @Override
            public void onChanged(ArrayList<ModelMeal> modelMeals) {
                viewInterface.onSuccess(modelMeals.get(0));
            }
        });
    }

    public void insertMeal(ModelMeal modelMeal) {
        mealRepo.insertMeal(modelMeal)
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

    public void deleteMeal(ModelMeal modelMeal) {
        mealRepo.deleteMeal(modelMeal)
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

    public void insertMealToFireBase(ModelMeal modelMeal) {
        ref.child(MySharedPref.getUserId())
                .child(Constants.FAV_REF)
                .child(modelMeal.getIdMeal())
                .setValue(modelMeal);
    }

    public void deleteMealFromFireBase(ModelMeal modelMeal) {
        ref.child(MySharedPref.getUserId())
                .child(Constants.FAV_REF)
                .child(modelMeal.getIdMeal())
                .removeValue();
    }

    public Single<ModelMeal> isFav(String mealId) {
        return mealRepo.isFav(mealId)
                .subscribeOn(Schedulers.io());
    }


}
