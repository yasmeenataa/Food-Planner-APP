package com.example.foodplannerapp.nework;

import com.example.foodplannerapp.models.CategoriesModel;
import com.example.foodplannerapp.models.ModelMeal;

import java.util.List;

import com.example.foodplannerapp.models.AreaListModel;
import com.example.foodplannerapp.models.IngredientListModel;

public interface NetworkDelegate {

    void onSuccessfulResult(List<ModelMeal> mealList);

    void onCategorySuccessfulResult(List<CategoriesModel> categoryList);

    void onFailureResult(String errorMessage);

    void onSuccessIngredientList(List<IngredientListModel> ingredientList);
    void onFailureIngredientList(String errorMsg);
    void onSuccessAreaList(List<AreaListModel> AreaList);
    void onFailureAreaList(String errorMsg);

}
