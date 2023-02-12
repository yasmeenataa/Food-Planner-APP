package com.example.foodplannerapp.ui.dayFragment.view;

import com.example.foodplannerapp.models.WeekPlannerModel;

public interface DayViewInterface {

    void insertMealToWeek(WeekPlannerModel weekPlannerModel);

    void getErrorMessage(String error);
}
