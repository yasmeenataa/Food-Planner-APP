package com.example.foodplannerapp.nework;

import com.example.foodplannerapp.models.ModelMeal;

import java.util.List;

public interface NetworkDelegateForIngredientItem {
    void onIngredientNameSuccessfulResult(List<ModelMeal> modelMealsFromIngredientList);

    void onFailureIngredientNameResult(String errorMessage);
}

