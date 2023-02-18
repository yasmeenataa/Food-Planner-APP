package com.example.foodplannerapp.ui.resulFromSearch.presenter;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public interface ResultSearchPresenterInterface {
    void getAllMealCategory(String categoryName);
    void getAllMealArea(String areaName);
    void getAllMealIngredient(String ingredientName);

    CompositeDisposable getDisposable();
}