package com.example.foodplannerapp.nework;

import com.example.foodplannerapp.models.CategoriesModel;
import com.example.foodplannerapp.models.ModelMeal;

import java.util.List;

public interface NetworkDelegate {

    void onSuccessfulResult(List<ModelMeal> mealList);

    void onCategorySuccessfulResult(List<CategoriesModel> categoryList);

    void onFailureResult(String errorMessage);

}
