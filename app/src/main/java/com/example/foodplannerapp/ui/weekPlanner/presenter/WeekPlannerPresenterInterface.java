package com.example.foodplannerapp.ui.weekPlanner.presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplannerapp.models.ModelMeal;
import com.example.foodplannerapp.models.WeekPlannerModel;

import java.util.List;

public interface WeekPlannerPresenterInterface {
    LiveData<List<WeekPlannerModel>> getAllMealByDay(String day);

    void deleteWeeklyMeal(WeekPlannerModel weekPlannerModel);

    void deleteWeeklyMealFromFirebase(WeekPlannerModel weekPlannerModel);
}
