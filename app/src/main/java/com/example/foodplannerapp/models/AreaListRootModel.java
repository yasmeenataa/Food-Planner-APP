package com.example.foodplannerapp.models;

import java.util.ArrayList;

public class AreaListRootModel {
    private ArrayList<AreaListModel> meals;

    public AreaListRootModel() {
    }

    public ArrayList<AreaListModel> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<AreaListModel> meals) {
        this.meals = meals;
    }
}
