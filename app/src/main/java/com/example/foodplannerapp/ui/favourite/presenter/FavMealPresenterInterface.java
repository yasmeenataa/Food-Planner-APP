package com.example.foodplannerapp.ui.favourite.presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplannerapp.models.ModelMeal;

import java.util.List;

public interface FavMealPresenterInterface {

    LiveData<List<ModelMeal>> getAllMeal();

    void deleteFavMeal(ModelMeal modelMeal);

    void deleteFavMealFromFireBase(ModelMeal modelMeal);
}
