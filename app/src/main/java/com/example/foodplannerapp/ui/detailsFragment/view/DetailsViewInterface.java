package com.example.foodplannerapp.ui.detailsFragment.view;

import com.example.foodplannerapp.models.ModelMeal;

public interface DetailsViewInterface {

    void onSuccess(ModelMeal modelMeal);

    void insertMealToFav(ModelMeal modelMeal);

//    void deleteMealFromFav(ModelMeal modelMeal);

    void getProgressBarLiveData();



}
