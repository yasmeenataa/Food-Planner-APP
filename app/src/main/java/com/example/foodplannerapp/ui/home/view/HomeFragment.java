package com.example.foodplannerapp.ui.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.databinding.FragmentHomeBinding;
import com.example.foodplannerapp.models.CategoriesModel;
import com.example.foodplannerapp.models.ModelMeal;
import com.example.foodplannerapp.models.MySharedPref;
import com.example.foodplannerapp.repo.mealRepo.MealRepo;
import com.example.foodplannerapp.ui.home.presenter.PresenterHome;
import com.example.foodplannerapp.ui.home.presenter.PresenterHomeInterface;
//import com.example.foodplannerapp.ui.home.view.HomeFragmentDirections;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements HomeViewInterface {

    private FragmentHomeBinding binding;
    private HomeFragment fragment;
    private CategoriesAdapter adapter;
    private String mealName, mealCountry, mealCategory, imageUrl, mealId;

    private ModelMeal modelMeal;

    private PresenterHomeInterface presenterInterface;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.e("TAG", MySharedPref.getUserName());
        Log.e("TAG", MySharedPref.getUserId());
        Log.e("TAG", MySharedPref.getUserEmail());
        Log.e("TAG", MySharedPref.getUserPassword());
        Log.e("TAG", MySharedPref.getUserUriKey());

        fragment = new HomeFragment();
        adapter = new CategoriesAdapter();

        presenterInterface = new PresenterHome(this);
        presenterInterface.getRandomMeal();

        presenterInterface.getAllCategories();
        onClicks();
    }

    private void showHideMealOfTheDayProgress() {
        binding.rootMealOfTheDay.setVisibility(View.INVISIBLE);
        binding.progressMealOfTheDay.setVisibility(View.VISIBLE);
        new Handler().postDelayed(() -> {
            binding.progressMealOfTheDay.setVisibility(View.GONE);
            binding.rootMealOfTheDay.setVisibility(View.VISIBLE);
        }, 3000);
    }

    private void showHideCategoriesProgress() {
        binding.recycleCategory.setVisibility(View.INVISIBLE);
        binding.progressCategories.setVisibility(View.VISIBLE);
        new Handler().postDelayed(() -> {
            binding.progressCategories.setVisibility(View.GONE);
            binding.recycleCategory.setVisibility(View.VISIBLE);
        }, 1500);
    }

    private void onClicks() {
        binding.cardViewMealHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragmentDirections.ActionHomeFragmentToDetailsFragment action =
                        HomeFragmentDirections.actionHomeFragmentToDetailsFragment(mealId);
                Navigation.findNavController(view).navigate(action);
            }
        });

        adapter.setOnCategoryClickListener(new CategoriesAdapter.SetOnCategoryClickListener() {
            @Override
            public void onCategoryClicked(String categoryName) {
                HomeFragmentDirections.ActionHomeFragmentToAllCategoriesFragment3 action =
                        HomeFragmentDirections.actionHomeFragmentToAllCategoriesFragment3(categoryName);
                Navigation.findNavController(requireView())
                        .navigate(action);

            }
        });
    }


    @Override
    public void showMealOfTheDay(ModelMeal modelMeal) {
        mealId = modelMeal.getIdMeal();
        mealName = modelMeal.getStrMeal();
        mealCategory = modelMeal.getStrCategory();
        mealCountry = modelMeal.getStrArea();
        imageUrl = modelMeal.getStrMealThumb();


        binding.textViewArea.setText(mealCountry);
        binding.textViewcategory.setText(mealCategory);
        binding.textViewMealName.setText(mealName);
        Glide.with(requireContext())
                .load(imageUrl)
                .into(binding.imageMeal);
//        binding.progressCategories.setVisibility(View.GONE);
//        binding.progressMealOfTheDay.setVisibility(View.GONE);

    }

    @Override
    public void showAllCategories(List<CategoriesModel> categoryList) {
//        binding.progressCategories.setVisibility(View.GONE);
//        binding.progressMealOfTheDay.setVisibility(View.GONE);
        adapter.setList((ArrayList<CategoriesModel>) categoryList);
        binding.recycleCategory.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getErrorMessage(String message) {
        binding.progressCategories.setVisibility(View.VISIBLE);
        binding.progressMealOfTheDay.setVisibility(View.VISIBLE);
        Toast.makeText(requireContext(), "Failed : Check Network", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getProgressBarLiveData() {
        presenterInterface.getProgressBarLiveData()
                .observe(getViewLifecycleOwner(), new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        //        showHideCategoriesProgress();
                        //        showHideMealOfTheDayProgress();
                        binding.progressCategories.setVisibility(integer);
                        binding.progressMealOfTheDay.setVisibility(integer);
                    }
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

