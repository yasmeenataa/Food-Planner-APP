package com.example.foodplannerapp.ui.resulFromSearch.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.databinding.FragmentRasultSearchBinding;
import com.example.foodplannerapp.models.ModelMeal;
import com.example.foodplannerapp.repo.mealRepo.MealRepo;
import com.example.foodplannerapp.ui.resulFromSearch.presenter.ResultSearchPresenter;
import com.example.foodplannerapp.ui.resulFromSearch.presenter.ResultSearchPresenterInterface;
import com.example.foodplannerapp.ui.resulFromSearch.view.ResultSearchFragmentDirections;


import java.util.ArrayList;
import java.util.List;


public class ResultSearchFragment extends Fragment implements ResultSearchViewInterface, OnResultClickListener {
    FragmentRasultSearchBinding binding;
    String item_type, item_name;
    ResultSearchAdapter resultSearchAdapter;
    ResultSearchPresenterInterface presenterInterface;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        item_type = ResultSearchFragmentArgs.fromBundle(getArguments()).getItemType();
        item_name = ResultSearchFragmentArgs.fromBundle(getArguments()).getItemName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentRasultSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onClicks();
        binding.txtCategoryName.setText(item_name + " Recipes");
        presenterInterface = new ResultSearchPresenter(this, MealRepo.getMealRepoInstance());
        resultSearchAdapter = new ResultSearchAdapter(new ArrayList<>(), getContext(), this);
        System.out.println("////////////////////////////////////" + item_type + "//////" + item_name);
        if (item_type.equals("Area")) {
            presenterInterface.getAllMealArea(item_name);
        } else if (item_type.equals("Ingredient"))
            presenterInterface.getAllMealIngredient(item_name);
        else if (item_type.equals("Category"))
            presenterInterface.getAllMealCategory(item_name);

    }

    private void onClicks() {
        binding.btnBackResultSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v)
                        .popBackStack();
            }
        });
    }

    @Override
    public void onMealClick(String mealId) {
        com.example.foodplannerapp.ui.resulFromSearch.view.ResultSearchFragmentDirections.ActionResultSearchFragmentToDetailsFragment action =
                com.example.foodplannerapp.ui.resulFromSearch.view.ResultSearchFragmentDirections.actionResultSearchFragmentToDetailsFragment(mealId);
        Navigation.findNavController(requireView())
                .navigate(action);

    }

    @Override
    public void showAllMealCategory(List<ModelMeal> mealList) {
        resultSearchAdapter.setList((ArrayList<ModelMeal>) mealList);
        binding.recycleResult.setAdapter(resultSearchAdapter);
        resultSearchAdapter.notifyDataSetChanged();
    }

    @Override
    public void showAllMealArea(List<ModelMeal> mealList) {
        resultSearchAdapter.setList((ArrayList<ModelMeal>) mealList);
        binding.recycleResult.setAdapter(resultSearchAdapter);
        resultSearchAdapter.notifyDataSetChanged();
    }

    @Override
    public void showAllMealIngredient(List<ModelMeal> mealList) {
        resultSearchAdapter.setList((ArrayList<ModelMeal>) mealList);
        binding.recycleResult.setAdapter(resultSearchAdapter);
        resultSearchAdapter.notifyDataSetChanged();
    }

    @Override
    public void getErrorMessageCat(String message) {
        System.out.println("Error :" + message);
    }

    @Override
    public void getErrorMessageIng(String message) {
        System.out.println("Error :" + message);
    }

    @Override
    public void getErrorMessageArea(String message) {
        System.out.println("Error :" + message);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        presenterInterface.getDisposable().clear();
    }
}