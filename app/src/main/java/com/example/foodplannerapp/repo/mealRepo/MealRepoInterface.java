package com.example.foodplannerapp.repo.mealRepo;

import com.example.foodplannerapp.models.ModelMealRoot;
import com.example.foodplannerapp.nework.NetworkDelegate;
import com.example.foodplannerapp.nework.NetworkDelegateForArea;
import com.example.foodplannerapp.nework.NetworkDelegateForCategory;
import com.example.foodplannerapp.nework.NetworkDelegateForIngredient;

import io.reactivex.rxjava3.core.Single;

public interface MealRepoInterface {

    void getRandomMeal(NetworkDelegate networkDelegate);

    void getRandomMeal2();

    void getMealById(String mealId);

    void getAllCategory(NetworkDelegate networkDelegate);
    void getAllArea(NetworkDelegateForArea delegateForArea);
    void getAllIngredient(NetworkDelegateForIngredient delegateForIngredient);

    void getMealsOfCategory(NetworkDelegateForCategory networkDelegateForCategory , String catName);
}
