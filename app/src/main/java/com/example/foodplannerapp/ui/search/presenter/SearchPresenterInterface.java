package com.example.foodplannerapp.ui.search.presenter;

public interface SearchPresenterInterface {
    void getIngredientList();
    void getAllCategories();

    void getAreaList();
    void getMealByName(String mealName);

}
