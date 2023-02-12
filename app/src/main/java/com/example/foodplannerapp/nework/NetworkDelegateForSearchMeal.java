package com.example.foodplannerapp.nework;

import com.example.foodplannerapp.models.ModelMeal;

import java.util.List;

public interface NetworkDelegateForSearchMeal {
    void onSuccessSearchMealByName(List<ModelMeal> modelMealsFromCategoriesList);

    void onFailureSearchMealByName(String errorMessage);
}
