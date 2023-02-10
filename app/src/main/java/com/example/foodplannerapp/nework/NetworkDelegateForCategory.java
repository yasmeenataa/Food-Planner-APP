package com.example.foodplannerapp.nework;

import com.example.foodplannerapp.models.ModelMeal;

import java.util.List;

public interface NetworkDelegateForCategory {

    void onCategoryNameSuccessfulResult(List<ModelMeal> modelMealsFromCategoriesList);

    void onFailureResult(String errorMessage);

}
