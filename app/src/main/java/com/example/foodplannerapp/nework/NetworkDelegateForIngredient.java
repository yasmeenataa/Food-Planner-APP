package com.example.foodplannerapp.nework;

import com.example.foodplannerapp.models.IngredientListModel;

import java.util.List;

public interface NetworkDelegateForIngredient {
    void onSuccessIngredientList(List<IngredientListModel> ingredientList);
    void onFailureIngredientList(String errorMsg);
}
