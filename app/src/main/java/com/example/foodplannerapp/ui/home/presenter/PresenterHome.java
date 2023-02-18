package com.example.foodplannerapp.ui.home.presenter;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
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

import io.reactivex.rxjava3.disposables.CompositeDisposable;

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
    public void getAllCategories() {
        mealRepo.getAllCategory(this);
    }

    @Override
    public MutableLiveData<Integer> getProgressBarLiveData() {
        return mealRepo.getProgressLiveData();
    }

    @Override
    public CompositeDisposable getDisposable() {
        return mealRepo.getDisposable();
    }

    @Override
    public void onSuccessfulResult(List<ModelMeal> mealList) {
        viewInterface.showMealOfTheDay(mealList.get(0));
    }

    @Override
    public void onCategorySuccessfulResult(List<CategoriesModel> categoryList) {
        viewInterface.showAllCategories(categoryList);
    }


    @Override
    public void onFailureResult(String errorMessage) {
        viewInterface.getErrorMessage(errorMessage);
    }


}
