package com.example.foodplannerapp.ui.search.view;

import com.example.foodplannerapp.models.AreaListModel;
import com.example.foodplannerapp.models.CategoriesModel;
import com.example.foodplannerapp.models.IngredientListModel;

import java.util.List;

public interface SearchViewInterface {
    void showIngredientList(List<IngredientListModel> ingredientList);
    void showCategoryList(List<CategoriesModel> categoriesList);
    void showAreaList(List<AreaListModel> areaList);
    void onFailureIngredientList(String errorMsg);
    void onFailureAreaList(String errorMsg);

}
