package com.example.foodplannerapp.ui.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplannerapp.CategoriesAdapter;
import com.example.foodplannerapp.databinding.FragmentSearchBinding;
import com.example.foodplannerapp.models.CategoriesModel;
import com.example.foodplannerapp.models.MealModel;
import com.example.foodplannerapp.ui.favourite.FavouriteAdapter;

import java.util.ArrayList;


public class SearchFragment extends Fragment {
    private FragmentSearchBinding binding;
    private ArrayList<CategoriesModel> list;
    private CategoriesAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);


        list = new ArrayList<>();
        inflateRecyclerView();

    }

    private ArrayList<CategoriesModel> fillList() {
        CategoriesModel categoriesModel = new CategoriesModel();
        for (int i = 0; i <= 10; i++) {
            categoriesModel.setStrCategory("Beef" + i);
            categoriesModel.setStrCategoryThumb("https:\\/\\/www.themealdb.com\\/images\\/category\\/beef.png" + i);
            list.add(i, categoriesModel);
        }
        return list;
    }

    private void inflateRecyclerView() {
        adapter=new CategoriesAdapter(fillList(),getContext());

        binding.recyclerSearchCat.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}