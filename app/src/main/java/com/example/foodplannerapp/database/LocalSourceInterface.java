package com.example.foodplannerapp.database;

import androidx.lifecycle.LiveData;

import com.example.foodplannerapp.models.ModelMeal;
import com.example.foodplannerapp.models.WeekPlannerModel;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface LocalSourceInterface {

    Completable insertMeal(ModelMeal modelMeal);
    Completable removeMeal(ModelMeal modelMeal);
    LiveData<List<ModelMeal>> getFavProduct();

    LiveData<List<WeekPlannerModel>> getWeeKMeals(String day);

    Completable insertWeekPlannerMeal(WeekPlannerModel weekPlannerModel);
    Completable removeWeekPlannerMeal(WeekPlannerModel weekPlannerModel);

    Completable deleteFavTableRoom();
    Completable deleteWeekTableRoom();

    Single<ModelMeal> isFav(String mealId);
}
