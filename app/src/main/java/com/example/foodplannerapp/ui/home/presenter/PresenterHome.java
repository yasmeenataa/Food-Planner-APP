package com.example.foodplannerapp.ui.home.presenter;

import android.util.Log;

import androidx.lifecycle.Observer;

import com.example.foodplannerapp.models.AreaListModel;
import com.example.foodplannerapp.models.CategoriesModel;
import com.example.foodplannerapp.models.IngredientListModel;
import com.example.foodplannerapp.models.ModelMeal;
import com.example.foodplannerapp.nework.NetworkDelegate;
import com.example.foodplannerapp.repo.mealRepo.MealRepo;
import com.example.foodplannerapp.ui.home.view.HomeViewInterface;

import java.util.ArrayList;
import java.util.List;

public class PresenterHome implements PresenterHomeInterface, NetworkDelegate {


    private final HomeViewInterface viewInterface;
    private final MealRepo mealRepo;


    public PresenterHome(HomeViewInterface viewInterface) {
        mealRepo = MealRepo.getMealRepoInstance();
        this.viewInterface = viewInterface;
    }

    @Override
    public void getRandomMeal() {
        mealRepo.getRandomMeal(this);
    }

    @Override
    public void getRandomMeal2() {
        mealRepo.getRandomMeal2();
    }

    @Override
    public void getAllCategories() {
    mealRepo.getAllCategory(this);
    }

    @Override
    public void onSuccessfulResult(List<ModelMeal> mealList) {
        viewInterface.showMeal(mealList.get(0));
    }

    @Override
    public void onCategorySuccessfulResult(List<CategoriesModel> categoryList) {
        viewInterface.showAllCategories(categoryList);
    }



    @Override
    public void onFailureResult(String errorMessage) {
        viewInterface.getErrorMessage(errorMessage);
    }

    @Override
    public void onSuccessIngredientList(List<IngredientListModel> ingredientList) {

    }

    @Override
    public void onFailureIngredientList(String errorMsg) {

    }

    @Override
    public void onSuccessAreaList(List<AreaListModel> AreaList) {

    }

    @Override
    public void onFailureAreaList(String errorMsg) {

    }
}
