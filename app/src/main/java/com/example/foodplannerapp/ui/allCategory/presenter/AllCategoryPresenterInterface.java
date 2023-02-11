package com.example.foodplannerapp.ui.allCategory.presenter;

import com.example.foodplannerapp.models.ModelMeal;

public interface AllCategoryPresenterInterface {

    void getAllMealCategory(String categoryName);

    void insertToFav(ModelMeal modelMeal);
}
