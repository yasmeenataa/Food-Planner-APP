package com.example.foodplannerapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplannerapp.models.MealModel;

import java.util.List;

@Dao
public interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeal (MealModel mealModel);


    @Query("select * from MEAL_TABLE where userIds Like :userId")
    LiveData<List<MealModel>> getFavouriteMeals(String userId);

}
