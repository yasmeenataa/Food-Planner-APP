package com.example.foodplannerapp.ui.search.presenter;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public interface SearchPresenterInterface {
    void getIngredientList();
    void getAllCategories();

    void getAreaList();
    void getMealByName(String mealName);

    CompositeDisposable getDisposable();

}
