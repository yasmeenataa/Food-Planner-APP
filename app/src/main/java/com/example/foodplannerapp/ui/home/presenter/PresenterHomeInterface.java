package com.example.foodplannerapp.ui.home.presenter;

import androidx.lifecycle.MutableLiveData;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public interface PresenterHomeInterface {

    void getRandomMeal();

    void getAllCategories();

    MutableLiveData<Integer> getProgressBarLiveData();

    CompositeDisposable getDisposable();
}
