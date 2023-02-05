package com.example.foodplannerapp.ui.favourite;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.databinding.FragmentFavouriteBinding;
import com.example.foodplannerapp.databinding.FragmentWelcomeBinding;
import com.example.foodplannerapp.models.MealModel;

import java.util.ArrayList;


public class FavouriteFragment extends Fragment {
    private FragmentFavouriteBinding binding;
    private ArrayList<MealModel> list;
    private FavouriteAdapter adapter;

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
        adapter = new FavouriteAdapter();
        list = new ArrayList<>();
        onClicks();
        inflateRecyclerView();


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
                Navigation.findNavController(getView()).navigate(R.id.action_favouriteFragment_to_detailsFragment);
            }

            @Override
            public void onItemDelete(MealModel mealModel) {
                Toast.makeText(requireContext(), "Item is Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void inflateRecyclerView() {
//        fillList();
        adapter.setList(fillList());
        binding.recyclerFav.setAdapter(adapter);
    }

    private ArrayList<MealModel> fillList() {
        MealModel mealModel = new MealModel();
        for (int i = 0; i <= 10; i++) {
            mealModel.setStrCategory("Garlic" + i);
            mealModel.setStrMeal("Chicken" + i);
            list.add(i, mealModel);
        }
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}