package com.example.foodplannerapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplannerapp.models.ModelMeal;
import com.example.foodplannerapp.models.WeekPlannerModel;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertMeal(ModelMeal mealModel);


    @Delete
    Completable deleteMeal(ModelMeal modelMeal);


    @Query("select * from MEAL_TABLE")
    LiveData<List<ModelMeal>> getFavProduct();

    @Query("select *  from WEEK_TABLE where day like :day")
    LiveData<List<WeekPlannerModel>> getWeekPlannerMeal(String day);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertWeekPlannerMeal(WeekPlannerModel weekPlannerModel);

    @Delete
    Completable deleteWeekPlannerMeal(WeekPlannerModel weekPlannerModel);

    @Query("DELETE FROM MEAL_TABLE")
    Completable deleteFavTableRoom();

    @Query("DELETE FROM WEEK_TABLE")
    Completable deleteWeekTableRoom();

    @Query("select * from MEAL_TABLE where idMeal = :mealId")
    Single<ModelMeal> isFav(String mealId);


}
