package com.example.foodplannerapp.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplannerapp.ui.search.view.CategoriesAdapter;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.databinding.FragmentHomeBinding;
import com.example.foodplannerapp.models.CategoriesModel;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ArrayList<CategoriesModel> list;
    private CategoriesAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list = new ArrayList<>();
        inflateRecyclerView();
        onClicks();

    }

    private void onClicks() {
        binding.cardViewMealHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_detailsFragment);
            }
        });
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

        binding.recycleCategory.setAdapter(adapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}