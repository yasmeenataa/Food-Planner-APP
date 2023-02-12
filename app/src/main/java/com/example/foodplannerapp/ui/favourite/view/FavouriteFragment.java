package com.example.foodplannerapp.ui.favourite.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.databinding.FragmentFavouriteBinding;
import com.example.foodplannerapp.models.ModelMeal;
import com.example.foodplannerapp.models.MySharedPref;
import com.example.foodplannerapp.models.WeekPlannerModel;
import com.example.foodplannerapp.repo.mealRepo.MealRepo;
import com.example.foodplannerapp.ui.favourite.presenter.FavMealPresenter;
import com.example.foodplannerapp.ui.favourite.presenter.FavMealPresenterInterface;
import com.example.foodplannerapp.ui.favourite.view.FavouriteAdapter;
import com.example.foodplannerapp.ui.favourite.view.FavouriteFragmentDirections;
import com.example.foodplannerapp.ui.favourite.view.FavouriteFragmentDirections;
import com.example.foodplannerapp.ui.favourite.view.FavouriteFragmentDirections.ActionFavouriteFragmentToDetailsFragment;
import com.example.foodplannerapp.ui.home.view.HomeFragmentDirections;
import com.example.foodplannerapp.utils.Constants;
import com.example.foodplannerapp.utils.Extensions;

import java.util.ArrayList;
import java.util.List;


public class FavouriteFragment extends Fragment implements FavViewInterface {
    private FragmentFavouriteBinding binding;
    private FavouriteAdapter adapter;

    private FavMealPresenterInterface presenterInterface;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (MySharedPref.getUserId().isEmpty()) {
            Toast.makeText(requireContext(), "You Have to Login First !!", Toast.LENGTH_LONG).show();
        } else {
            adapter = new FavouriteAdapter();
            presenterInterface = new FavMealPresenter(this);
            getData();
            onClicks();
        }

    }

    private void onClicks() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_favouriteFragment_to_homeFragment);
            }
        });

        adapter.setOnItemListener(new FavouriteAdapter.setOnItemListener() {
            @Override
            public void onItemClick(String id) {
                FavouriteFragmentDirections.ActionFavouriteFragmentToDetailsFragment action =
                        FavouriteFragmentDirections.actionFavouriteFragmentToDetailsFragment(id);
                Navigation.findNavController(requireView()).navigate(action);
            }

            @Override
            public void onItemDelete(ModelMeal mealModel) {
                showDialog(mealModel);
            }
        });
    }

    private void getData() {
        presenterInterface.getAllMeal()
                .observe(getViewLifecycleOwner(), new Observer<List<ModelMeal>>() {
                    @Override
                    public void onChanged(List<ModelMeal> mealList) {
                        adapter.setList((ArrayList<ModelMeal>) mealList);
                        binding.recyclerFav.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    private void showDialog(ModelMeal model) {
        Extensions.showConfirmationDialog(requireContext(), Constants.DELETE_FAV_ITEM,
                //onYes
                () -> {
                    deleteMeal(model);
                    Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_SHORT).show();
                },
                //onNo
                () -> {
                }
        );

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void deleteMeal(ModelMeal modelMeal) {
        presenterInterface.deleteFavMeal(modelMeal);
        presenterInterface.deleteFavMealFromFireBase(modelMeal);
        Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_SHORT).show();
    }
}