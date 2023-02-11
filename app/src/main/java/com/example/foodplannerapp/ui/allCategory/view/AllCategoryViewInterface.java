package com.example.foodplannerapp.ui.allCategory.view;

import com.example.foodplannerapp.models.CategoriesModel;
import com.example.foodplannerapp.models.ModelMeal;

import java.util.List;

public interface AllCategoryViewInterface {

    void showAllMealCategory(List<ModelMeal> mealList);

    void getErrorMessage(String message);
}
