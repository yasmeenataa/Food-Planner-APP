package com.example.foodplannerapp.models;

import java.util.ArrayList;

public class MealRootModel {


    private ArrayList<MealModel> meals;

    public MealRootModel() {

    }

    public ArrayList<MealModel> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<MealModel> meals) {
        this.meals = meals;
    }
}
