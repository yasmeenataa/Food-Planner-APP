package com.example.foodplannerapp.ui.search.presenter;

import com.example.foodplannerapp.models.AreaListModel;
import com.example.foodplannerapp.models.IngredientListModel;
import com.example.foodplannerapp.models.RepoInterface;
import com.example.foodplannerapp.nework.NetworkDelegate;
import com.example.foodplannerapp.ui.search.view.SearchViewInterface;

import java.util.List;

public class SearchPresenter implements NetworkDelegate,SearchPresenterInterface {
    private SearchViewInterface _view;
    private RepoInterface _repo;

    public SearchPresenter(SearchViewInterface _view, RepoInterface _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getIngredientList() {

    }

    @Override
    public void getCategoryList() {

    }

    @Override
    public void getAreaList() {

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
