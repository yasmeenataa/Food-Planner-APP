package com.example.foodplannerapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplannerapp.models.ModelMeal;

import java.util.List;

@Dao
public interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeal (ModelMeal mealModel);


//    @Query("select * from ModelMeal where userIds Like :userId")
//    LiveData<List<ModelMeal>> getFavouriteMeals(String userId);

}
