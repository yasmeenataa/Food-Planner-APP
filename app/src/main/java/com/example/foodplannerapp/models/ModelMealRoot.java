package com.example.foodplannerapp.models;

import java.util.ArrayList;

public class ModelMealRoot {
    private ArrayList<ModelMeal> meals;

    public ModelMealRoot() {

    }

    public ArrayList<ModelMeal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<ModelMeal> meals) {
        this.meals = meals;
    }
}
