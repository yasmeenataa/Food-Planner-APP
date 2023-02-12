package com.example.foodplannerapp.ui.allCategory.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.databinding.FragmentAllCategoriesBinding;
import com.example.foodplannerapp.models.ModelMeal;
import com.example.foodplannerapp.ui.allCategory.presenter.AllCategoryPresenterInterface;
import com.example.foodplannerapp.ui.allCategory.presenter.PresenterAllCategory;
import com.example.foodplannerapp.ui.allCategory.view.AllCategoriesFragmentDirections;

import java.util.ArrayList;
import java.util.List;


public class AllCategoriesFragment extends Fragment implements AllCategoryViewInterface {

    private FragmentAllCategoriesBinding binding;
    private String categoryName;
    private AllCategoryPresenterInterface presenterInterface;

    private AdapterAllMealsFromCat adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        categoryName = AllCategoriesFragmentArgs.fromBundle(getArguments()).getCategoryName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAllCategoriesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenterInterface = new PresenterAllCategory(this);
        adapter = new AdapterAllMealsFromCat();
        onClicks();
        presenterInterface.getAllMealCategory(categoryName);


    }

    private void onClicks() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_allCategoriesFragment3_to_homeFragment);
            }
        });

        adapter.setOnMealClickListener(new AdapterAllMealsFromCat.SetOnMealClickListener() {
            @Override
            public void onMealClick(String mealId) {
                AllCategoriesFragmentDirections.ActionAllCategoriesFragment3ToDetailsFragment action =
                        AllCategoriesFragmentDirections.actionAllCategoriesFragment3ToDetailsFragment(mealId);
                Navigation.findNavController(requireView())
                        .navigate(action);

            }

        });
    }


    @Override
    public void showAllMealCategory(List<ModelMeal> mealList) {
        adapter.setList((ArrayList<ModelMeal>) mealList);
        binding.recyclerAllCategories.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getErrorMessage(String message) {
        Toast.makeText(requireContext(), "Fail: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

    }
}