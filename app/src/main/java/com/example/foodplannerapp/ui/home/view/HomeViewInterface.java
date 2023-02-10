package com.example.foodplannerapp.ui.home.view;

import com.example.foodplannerapp.models.CategoriesModel;
import com.example.foodplannerapp.models.ModelMeal;

import java.util.List;

public interface HomeViewInterface {

    void showMeal(ModelMeal modelMeal);

    void showAllCategories(List<CategoriesModel> categoryList);

    void getErrorMessage(String message);
}
