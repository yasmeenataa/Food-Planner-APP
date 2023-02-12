package com.example.foodplannerapp.ui.search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplannerapp.models.AreaListModel;
import com.example.foodplannerapp.models.IngredientListModel;
import com.example.foodplannerapp.repo.mealRepo.MealRepo;
import com.example.foodplannerapp.ui.home.view.*;
import com.example.foodplannerapp.databinding.FragmentSearchBinding;
import com.example.foodplannerapp.models.CategoriesModel;
import com.example.foodplannerapp.ui.search.view.SearchFragmentDirections;
import com.example.foodplannerapp.ui.favourite.view.FavouriteAdapter;
import com.example.foodplannerapp.ui.search.presenter.SearchPresenter;
import com.example.foodplannerapp.ui.search.presenter.SearchPresenterInterface;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment implements SearchViewInterface,OnItemClickListener {
    private FragmentSearchBinding binding;
    private ArrayList<CategoriesModel> list;
    private CategoriesAdapter categoriesAdapter;
    private AreaListAdapter areaListAdapter;
    private IngredientListAdapter ingredientListAdapter;
    private SearchPresenterInterface presenterInterface;
    private SearchFragment searchFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        searchFragment=new SearchFragment();
        areaListAdapter=new AreaListAdapter(new ArrayList<>(),getContext(),this);
        ingredientListAdapter=new IngredientListAdapter(new ArrayList<>(),getContext(),this);
        categoriesAdapter=new CategoriesAdapter();
        list = new ArrayList<>();
        presenterInterface=new SearchPresenter(this, MealRepo.getMealRepoInstance());
        presenterInterface.getAreaList();
        presenterInterface.getIngredientList();
        presenterInterface.getAllCategories();
        onClicks();


    }

public void onClicks()
{
    categoriesAdapter.setOnCategoryClickListener(new CategoriesAdapter.SetOnCategoryClickListener() {
        @Override
        public void onCategoryClicked(String categoryName) {
            SearchFragmentDirections.ActionSearchFragmentToAllCategoriesFragment3 action=
                    SearchFragmentDirections.actionSearchFragmentToAllCategoriesFragment3(categoryName);
            Navigation.findNavController(requireView())
                    .navigate(action);

        }
    });
}

    @Override
    public void showIngredientList(List<IngredientListModel> ingredientList) {
        binding.progressBarSearch.setVisibility(View.GONE);
        System.out.println("//////////////////////Handled/////////////////////"+ingredientList.get(0).getStrIngredient());
        ingredientListAdapter.setList((ArrayList<IngredientListModel>) ingredientList);
        binding.recycleIngredients.setAdapter(ingredientListAdapter);
        ingredientListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showAllCategories(List<CategoriesModel> categoryList) {
       categoriesAdapter.setList((ArrayList<CategoriesModel>) categoryList);
       binding.recyclerSearchCat.setAdapter(categoriesAdapter);
       categoriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailureCategoryList(String message) {
        System.out.println("Error : "+message);
        Toast.makeText(getContext(),"Error : "+message,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showAreaList(List<AreaListModel> areaList) {
        areaListAdapter.setList((ArrayList<AreaListModel>) areaList);
        binding.recycleArea.setAdapter(areaListAdapter);
        areaListAdapter.notifyDataSetChanged();

    }

    @Override
    public void onFailureIngredientList(String errorMsg) {
        System.out.println("Error : "+errorMsg);
        Toast.makeText(getContext(),"Error : "+errorMsg,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onFailureAreaList(String errorMsg) {
        System.out.println("Error : "+errorMsg);
        Toast.makeText(getContext(),"Error : "+errorMsg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnIngredientClick(String item_name) {
        SearchFragmentDirections.ActionSearchFragmentToResultSearchFragment action=
                SearchFragmentDirections.actionSearchFragmentToResultSearchFragment("Ingredient",item_name);
        Navigation.findNavController(requireView())
                .navigate(action);

    }

    @Override
    public void OnCategoryClick(String item_name) {


    }

    @Override
    public void OnAreaClick(String item_name) {
        SearchFragmentDirections.ActionSearchFragmentToResultSearchFragment action=
                SearchFragmentDirections.actionSearchFragmentToResultSearchFragment("Area",item_name);
        Navigation.findNavController(requireView())
                .navigate(action);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}