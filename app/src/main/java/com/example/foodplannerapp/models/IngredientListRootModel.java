package com.example.foodplannerapp.models;

import java.util.ArrayList;

public class IngredientListRootModel {
    private ArrayList<IngredientListModel> meals;

    public IngredientListRootModel() {
    }

    public IngredientListRootModel(ArrayList<IngredientListModel> meals) {
        this.meals = meals;

    }

    public ArrayList<IngredientListModel> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<IngredientListModel> meals) {
        this.meals = meals;
    }
}
