package com.example.foodplannerapp.ui.search.view;

public interface OnItemClickListener {
    void OnIngredientClick(String item_name);

    void OnAreaClick(String item_name);
    void onSearchedMealClick(String mealId);
}
