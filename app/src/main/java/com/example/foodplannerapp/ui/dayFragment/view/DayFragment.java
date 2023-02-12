package com.example.foodplannerapp.ui.dayFragment.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.databinding.FragmentDayBinding;
import com.example.foodplannerapp.models.ModelMeal;
import com.example.foodplannerapp.models.WeekPlannerModel;
import com.example.foodplannerapp.ui.dayFragment.presenter.DayPresenter;
import com.example.foodplannerapp.ui.dayFragment.presenter.DayPresenterInterface;

public class DayFragment extends Fragment implements DayViewInterface {

    private FragmentDayBinding binding;
    private String day;
    private ModelMeal model;

    private WeekPlannerModel weekPlannerModel;

    private DayPresenterInterface presenterInterface;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = DayFragmentArgs.fromBundle(getArguments()).getMealData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDayBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenterInterface = new DayPresenter(this);
        onClicks();
        weekPlannerModel = new WeekPlannerModel();

        setData();
    }

    private void setData() {
        weekPlannerModel.setStrInstructions(model.getStrInstructions());
        weekPlannerModel.setStrYoutube(model.getStrYoutube());
        weekPlannerModel.setUserId(model.getUserId());
        weekPlannerModel.setIdMeal(model.getIdMeal());
        weekPlannerModel.setStrArea(model.getStrArea());
        weekPlannerModel.setStrCategory(model.getStrCategory());
        weekPlannerModel.setStrMeal(model.getStrMeal());
        weekPlannerModel.setStrMealThumb(model.getStrMealThumb());
        weekPlannerModel.setStrIngredient1(model.getStrIngredient1());
        weekPlannerModel.setStrIngredient2(model.getStrIngredient2());
        weekPlannerModel.setStrIngredient3(model.getStrIngredient3());
        weekPlannerModel.setStrIngredient4(model.getStrIngredient4());
        weekPlannerModel.setStrIngredient5(model.getStrIngredient5());
        weekPlannerModel.setStrIngredient6(model.getStrIngredient6());
        weekPlannerModel.setStrIngredient7(model.getStrIngredient7());
        weekPlannerModel.setStrIngredient8(model.getStrIngredient8());
        weekPlannerModel.setStrIngredient9(model.getStrIngredient9());
        weekPlannerModel.setStrIngredient10(model.getStrIngredient10());
        weekPlannerModel.setStrIngredient11(model.getStrIngredient11());
        weekPlannerModel.setStrIngredient12(model.getStrIngredient12());
        weekPlannerModel.setStrIngredient13(model.getStrIngredient13());
        weekPlannerModel.setStrIngredient14(model.getStrIngredient14());
        weekPlannerModel.setStrIngredient15(model.getStrIngredient15());
        weekPlannerModel.setStrIngredient16(model.getStrIngredient16());
        weekPlannerModel.setStrIngredient17(model.getStrIngredient17());
        weekPlannerModel.setStrIngredient18(model.getStrIngredient18());
        weekPlannerModel.setStrIngredient19(model.getStrIngredient19());
        weekPlannerModel.setStrIngredient20(model.getStrIngredient12());
        weekPlannerModel.setStrMeasure1(model.getStrMeasure1());
        weekPlannerModel.setStrMeasure2(model.getStrMeasure2());
        weekPlannerModel.setStrMeasure3(model.getStrMeasure3());
        weekPlannerModel.setStrMeasure4(model.getStrMeasure4());
        weekPlannerModel.setStrMeasure5(model.getStrMeasure5());
        weekPlannerModel.setStrMeasure6(model.getStrMeasure6());
        weekPlannerModel.setStrMeasure7(model.getStrMeasure7());
        weekPlannerModel.setStrMeasure8(model.getStrMeasure8());
        weekPlannerModel.setStrMeasure9(model.getStrMeasure9());
        weekPlannerModel.setStrMeasure10(model.getStrMeasure10());
        weekPlannerModel.setStrMeasure11(model.getStrMeasure11());
        weekPlannerModel.setStrMeasure12(model.getStrMeasure12());
        weekPlannerModel.setStrMeasure13(model.getStrMeasure13());
        weekPlannerModel.setStrMeasure14(model.getStrMeasure14());
        weekPlannerModel.setStrMeasure15(model.getStrMeasure15());
        weekPlannerModel.setStrMeasure16(model.getStrMeasure16());
        weekPlannerModel.setStrMeasure17(model.getStrMeasure17());
        weekPlannerModel.setStrMeasure18(model.getStrMeasure18());
        weekPlannerModel.setStrMeasure19(model.getStrMeasure19());
        weekPlannerModel.setStrMeasure20(model.getStrMeasure12());
    }

    private void onClicks() {
        binding.textViewSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day = binding.textViewSaturday.getText().toString();
                weekPlannerModel.setDay(day);
                insertMealToWeek(weekPlannerModel);
            }
        });

        binding.textViewSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day = binding.textViewSaturday.getText().toString();
                weekPlannerModel.setDay(day);
                insertMealToWeek(weekPlannerModel);
            }
        });

        binding.textViewSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day = binding.textViewSunday.getText().toString();
                weekPlannerModel.setDay(day);
                insertMealToWeek(weekPlannerModel);
            }
        });

        binding.textViewMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day = binding.textViewMonday.getText().toString();
                weekPlannerModel.setDay(day);
            }
        });

        binding.textViewTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day = binding.textViewTuesday.getText().toString();
                weekPlannerModel.setDay(day);
                insertMealToWeek(weekPlannerModel);
            }
        });

        binding.textViewWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day = binding.textViewWednesday.getText().toString();
                weekPlannerModel.setDay(day);
                insertMealToWeek(weekPlannerModel);
            }
        });

        binding.textViewThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day = binding.textViewThursday.getText().toString();
                weekPlannerModel.setDay(day);
                insertMealToWeek(weekPlannerModel);
            }
        });

        binding.textViewFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day = binding.textViewFriday.getText().toString();
                weekPlannerModel.setDay(day);
                insertMealToWeek(weekPlannerModel);

            }
        });

        binding.backmageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view)
                        .navigate(R.id.action_dayFragment_to_homeFragment);
            }
        });
    }


    @Override
    public void insertMealToWeek(WeekPlannerModel weekPlannerModel) {
        presenterInterface.insertMealToDay(weekPlannerModel);
        presenterInterface.insertMealToDayToFirebase(weekPlannerModel);
        Toast.makeText(requireContext(), "Inserted into : " + weekPlannerModel.getDay(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getErrorMessage(String error) {
        Toast.makeText(requireContext(), "Error :" + error, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}