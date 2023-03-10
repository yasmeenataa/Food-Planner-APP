package com.example.foodplannerapp.repo.mealRepo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodplannerapp.models.ModelMeal;
import com.example.foodplannerapp.models.ModelMealRoot;
import com.example.foodplannerapp.models.WeekPlannerModel;
import com.example.foodplannerapp.nework.NetworkDelegate;
import com.example.foodplannerapp.nework.NetworkDelegateForArea;
import com.example.foodplannerapp.nework.NetworkDelegateForAreaItem;
import com.example.foodplannerapp.nework.NetworkDelegateForCategory;
import com.example.foodplannerapp.nework.NetworkDelegateForIngredient;
import com.example.foodplannerapp.nework.NetworkDelegateForIngredientItem;
import com.example.foodplannerapp.nework.NetworkDelegateForSearchMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public interface MealRepoInterface {

    void getRandomMeal(NetworkDelegate networkDelegate);
    void getMealById(String mealId);
    void getAllCategory(NetworkDelegate networkDelegate);
    void getAllArea(NetworkDelegateForArea delegateForArea);
    void getAllIngredient(NetworkDelegateForIngredient delegateForIngredient);

    void getMealsOfCategory(NetworkDelegateForCategory networkDelegateForCategory , String catName);
    void getMealsOfArea(NetworkDelegateForAreaItem delegateForAreaItem , String areaName);
    void getMealsOfIngredient(NetworkDelegateForIngredientItem delegateForIngredientItem , String ingredientName);
    Completable insertMeal(ModelMeal modelMeal);
    Completable deleteMeal(ModelMeal modelMeal);
    LiveData<List<ModelMeal>> getFavMeals();
    LiveData<List<WeekPlannerModel>> getPlannerMeals(String day);
    Completable insertWeekMeal(WeekPlannerModel weekPlannerModel);
    Completable deleteWeekMeal(WeekPlannerModel weekPlannerModel);
    Completable deleteFavTableRoom();
    Completable deleteWeekTableRoom();
    Single<ModelMeal> isFav(String mealId);
    void getMealByName(NetworkDelegateForSearchMeal delegateForSearchMeal,String mealName);

    MutableLiveData<Integer>  getProgressLiveData();

    CompositeDisposable getDisposable();


}
