package com.example.foodplannerapp.ui.favourite.presenter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodplannerapp.models.ModelMeal;

import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public interface FavMealPresenterInterface {

    LiveData<List<ModelMeal>> getAllMeal();

    void deleteFavMeal(ModelMeal modelMeal);

    void deleteFavMealFromFireBase(ModelMeal modelMeal);

    CompositeDisposable getDisposable();

    MutableLiveData<Integer> getProgressLiveData();
}
