package com.example.foodplannerapp.ui.weekPlanner.view;

import com.example.foodplannerapp.models.ModelMeal;
import com.example.foodplannerapp.models.WeekPlannerModel;

import java.util.List;

public interface WeekPlannerViewInterface {
    void deleteWeeklyMeal(WeekPlannerModel weekPlannerModel);

    void getError(String error);

}
