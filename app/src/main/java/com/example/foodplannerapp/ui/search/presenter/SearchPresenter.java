package com.example.foodplannerapp.ui.search.presenter;

import com.example.foodplannerapp.models.AreaListModel;
import com.example.foodplannerapp.models.CategoriesModel;
import com.example.foodplannerapp.models.IngredientListModel;
import com.example.foodplannerapp.models.ModelMeal;

import com.example.foodplannerapp.nework.NetworkDelegate;
import com.example.foodplannerapp.nework.NetworkDelegateForArea;
import com.example.foodplannerapp.nework.NetworkDelegateForIngredient;
import com.example.foodplannerapp.repo.mealRepo.MealRepoInterface;
import com.example.foodplannerapp.ui.search.view.SearchViewInterface;

import java.util.List;

public class SearchPresenter implements NetworkDelegateForArea, NetworkDelegateForIngredient,NetworkDelegate,SearchPresenterInterface {
    private SearchViewInterface _view;
    private MealRepoInterface _repo;

    public SearchPresenter(SearchViewInterface _view, MealRepoInterface _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getIngredientList() {
        _repo.getAllIngredient(this);
    }

    @Override
    public void getAllCategories() {
        _repo.getAllCategory(this);

    }


    @Override
    public void getAreaList() {
        _repo.getAllArea(this);
    }



    @Override
    public void onSuccessIngredientList(List<IngredientListModel> ingredientList) {
        _view.showIngredientList(ingredientList);
    }

    @Override
    public void onFailureIngredientList(String errorMsg) {
        _view.onFailureIngredientList(errorMsg);
    }

    @Override
    public void onSuccessAreaList(List<AreaListModel> areaList) {
        _view.showAreaList(areaList);
    }

    @Override
    public void onFailureAreaList(String errorMsg) {
        _view.onFailureAreaList(errorMsg);
    }

    @Override
    public void onSuccessfulResult(List<ModelMeal> mealList) {

    }

    @Override
    public void onCategorySuccessfulResult(List<CategoriesModel> categoryList) {
        _view.showAllCategories(categoryList);
    }

    @Override
    public void onFailureResult(String errorMessage) {

    }
}
