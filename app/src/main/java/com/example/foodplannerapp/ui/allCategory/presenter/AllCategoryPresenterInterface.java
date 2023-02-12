package com.example.foodplannerapp.ui.allCategory.presenter;

import androidx.lifecycle.MutableLiveData;

import com.example.foodplannerapp.models.ModelMeal;

public interface AllCategoryPresenterInterface {

    void getAllMealCategory(String categoryName);

    void insertToFav(ModelMeal modelMeal);

    MutableLiveData<Integer> getProgressBarLiveData();
}
