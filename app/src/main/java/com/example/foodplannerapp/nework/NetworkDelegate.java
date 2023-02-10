package com.example.foodplannerapp.nework;

import com.example.foodplannerapp.models.AreaListModel;
import com.example.foodplannerapp.models.IngredientListModel;

import java.util.List;

public interface NetworkDelegate {
    void onSuccessIngredientList(List<IngredientListModel> ingredientList);
    void onFailureIngredientList(String errorMsg);
    void onSuccessAreaList(List<AreaListModel> AreaList);
    void onFailureAreaList(String errorMsg);

}
