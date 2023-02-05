package com.example.foodplannerapp.ui.detailsFragment;

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
import com.example.foodplannerapp.databinding.FragmentDetailsBinding;
import com.example.foodplannerapp.models.MealModel;

import java.util.ArrayList;


public class DetailsFragment extends Fragment {

    private FragmentDetailsBinding binding;
    private IngredientsAdapter adapter;
    private ArrayList<MealModel> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new IngredientsAdapter();
        list = new ArrayList<>();

        onClicks();
        inflateRecyclerView();

    }

    private void onClicks() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_detailsFragment_to_homeFragment);
            }
        });
    }

    private void inflateRecyclerView() {
        adapter.setList(fillList());
        binding.recyclerIngredients.setAdapter(adapter);
    }

    private ArrayList<MealModel> fillList() {
        MealModel mealModel = new MealModel();
        for (int i = 0; i <= 10; i++) {
            mealModel.setStrIngredient1("Garlic" + i);
            mealModel.setStrMeasure1("Chicken" + i);
            list.add(i, mealModel);
        }
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}