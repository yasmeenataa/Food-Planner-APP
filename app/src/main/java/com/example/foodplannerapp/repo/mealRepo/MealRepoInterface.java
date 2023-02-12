package com.example.foodplannerapp.repo.mealRepo;

import androidx.lifecycle.LiveData;

import com.example.foodplannerapp.models.ModelMeal;
import com.example.foodplannerapp.models.ModelMealRoot;
import com.example.foodplannerapp.models.WeekPlannerModel;
import com.example.foodplannerapp.nework.NetworkDelegate;
import com.example.foodplannerapp.nework.NetworkDelegateForArea;
import com.example.foodplannerapp.nework.NetworkDelegateForAreaItem;
import com.example.foodplannerapp.nework.NetworkDelegateForCategory;
import com.example.foodplannerapp.nework.NetworkDelegateForIngredient;
import com.example.foodplannerapp.nework.NetworkDelegateForIngredientItem;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface MealRepoInterface {

    void getRandomMeal(NetworkDelegate networkDelegate);

    void getRandomMeal2();

    void getMealById(String mealId);

    void getAllCategory(NetworkDelegate networkDelegate);
    void getAllArea(NetworkDelegateForArea delegateForArea);
    void getAllIngredient(NetworkDelegateForIngredient delegateForIngredient);

    void getMealsOfCategory(NetworkDelegateForCategory networkDelegateForCategory , String catName);
    void getMealsOfArea(NetworkDelegateForAreaItem delegateForAreaItem , String areaName);
    void getMealsOfIngredient(NetworkDelegateForIngredientItem delegateForIngredientItem , String ingredientName);
    void getMealsOfCategory(NetworkDelegateForCategory networkDelegateForCategory, String catName);

    Completable insertMeal(ModelMeal modelMeal);

    Completable deleteMeal(ModelMeal modelMeal);

    LiveData<List<ModelMeal>> getFavMeals();

    LiveData<List<WeekPlannerModel>> getPlannerMeals(String day);

    Completable insertWeekMeal(WeekPlannerModel weekPlannerModel);

    Completable deleteWeekMeal(WeekPlannerModel weekPlannerModel);


    Completable deleteFavTableRoom();

    Completable deleteWeekTableRoom();


    Single<ModelMeal> isFav(String mealId);
}
