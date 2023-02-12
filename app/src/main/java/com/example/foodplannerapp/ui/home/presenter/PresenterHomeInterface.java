package com.example.foodplannerapp.ui.home.presenter;

import androidx.lifecycle.MutableLiveData;

public interface PresenterHomeInterface {

    void getRandomMeal();

    void getAllCategories();

    MutableLiveData<Integer> getProgressBarLiveData();
}
