package com.example.foodplannerapp.ui.resulFromSearch.view;

import com.example.foodplannerapp.models.ModelMeal;

import java.util.List;

public interface ResultSearchViewInterface {
    void showAllMealCategory(List<ModelMeal> mealList);
    void showAllMealArea(List<ModelMeal> mealList);
    void showAllMealIngredient(List<ModelMeal> mealList);
    void getErrorMessageCat(String message);
    void getErrorMessageIng(String message);
    void getErrorMessageArea(String message);
}
