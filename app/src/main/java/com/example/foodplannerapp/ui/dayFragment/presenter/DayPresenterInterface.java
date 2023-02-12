package com.example.foodplannerapp.ui.dayFragment.presenter;

import com.example.foodplannerapp.models.ModelMeal;
import com.example.foodplannerapp.models.WeekPlannerModel;

public interface DayPresenterInterface {

    void insertMealToDay(WeekPlannerModel weekPlannerModel);

    void insertMealToDayToFirebase(WeekPlannerModel weekPlannerModel);


}
