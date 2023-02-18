package com.example.foodplannerapp.ui.resulFromSearch.presenter;
import com.example.foodplannerapp.models.ModelMeal;
import com.example.foodplannerapp.nework.NetworkDelegateForAreaItem;
import com.example.foodplannerapp.nework.NetworkDelegateForCategory;
import com.example.foodplannerapp.nework.NetworkDelegateForIngredientItem;
import com.example.foodplannerapp.repo.mealRepo.MealRepo;
import com.example.foodplannerapp.ui.resulFromSearch.view.ResultSearchViewInterface;

import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class ResultSearchPresenter implements ResultSearchPresenterInterface, NetworkDelegateForIngredientItem, NetworkDelegateForAreaItem, NetworkDelegateForCategory {
    private final ResultSearchViewInterface viewInterface;
    private final MealRepo mealRepo;

    public ResultSearchPresenter(ResultSearchViewInterface viewInterface, MealRepo mealRepo) {
        this.viewInterface = viewInterface;
        this.mealRepo = mealRepo;
    }

    @Override
    public void onAreaNameSuccessfulResult(List<ModelMeal> modelMealsFromAreaList) {
        viewInterface.showAllMealArea(modelMealsFromAreaList);
    }

    @Override
    public void onFailureAreaNameResult(String errorMessage) {
        viewInterface.getErrorMessageArea(errorMessage);
    }

    @Override
    public void onIngredientNameSuccessfulResult(List<ModelMeal> modelMealsFromIngredientList) {
        viewInterface.showAllMealIngredient(modelMealsFromIngredientList);
    }

    @Override
    public void onFailureIngredientNameResult(String errorMessage) {
        viewInterface.getErrorMessageIng(errorMessage);
    }

    @Override
    public void getAllMealCategory(String categoryName) {
        mealRepo.getMealsOfCategory(this,categoryName);
    }

    @Override
    public void getAllMealArea(String areaName) {
        mealRepo.getMealsOfArea(this,areaName);
    }

    @Override
    public void getAllMealIngredient(String ingredientName) {
         mealRepo.getMealsOfIngredient(this,ingredientName);
    }

    @Override
    public CompositeDisposable getDisposable() {
        return mealRepo.getDisposable();
    }

    @Override
    public void onCategoryNameSuccessfulResult(List<ModelMeal> modelMealsFromCategoriesList) {
        viewInterface.showAllMealCategory(modelMealsFromCategoriesList);
    }

    @Override
    public void onFailureResult(String errorMessage) {
         viewInterface.getErrorMessageCat(errorMessage);
    }
}
