package com.example.foodplannerapp.ui.allCategory.presenter;

import androidx.lifecycle.MutableLiveData;

import com.example.foodplannerapp.models.ModelMeal;
import com.example.foodplannerapp.nework.NetworkDelegateForCategory;
import com.example.foodplannerapp.repo.mealRepo.MealRepo;
import com.example.foodplannerapp.ui.allCategory.view.AllCategoryViewInterface;
import com.example.foodplannerapp.ui.home.view.HomeViewInterface;

import java.util.List;

public class PresenterAllCategory implements AllCategoryPresenterInterface, NetworkDelegateForCategory {


    private final AllCategoryViewInterface viewInterface;
    private final MealRepo mealRepo;


    public PresenterAllCategory(AllCategoryViewInterface viewInterface) {
        mealRepo = MealRepo.getMealRepoInstance();
        this.viewInterface = viewInterface;
    }

    @Override
    public void onCategoryNameSuccessfulResult(List<ModelMeal> modelMealsFromCategoriesList) {
        viewInterface.showAllMealCategory(modelMealsFromCategoriesList);

    }

    @Override
    public void onFailureResult(String errorMessage) {
        viewInterface.getErrorMessage(errorMessage);

    }

    @Override
    public void getAllMealCategory(String catName) {
        mealRepo.getMealsOfCategory(this, catName);
    }

    @Override
    public void insertToFav(ModelMeal modelMeal) {
        mealRepo.insertMeal(modelMeal);
    }

    @Override
    public MutableLiveData<Integer> getProgressBarLiveData() {
        return mealRepo.getProgressLiveData();
    }
}
