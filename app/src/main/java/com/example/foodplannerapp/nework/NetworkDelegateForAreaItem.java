package com.example.foodplannerapp.nework;

import com.example.foodplannerapp.models.ModelMeal;

import java.util.List;

public interface NetworkDelegateForAreaItem {
    void onAreaNameSuccessfulResult(List<ModelMeal> modelMealsFromAreaList);

    void onFailureAreaNameResult(String errorMessage);
}
