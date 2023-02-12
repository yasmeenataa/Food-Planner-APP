package com.example.foodplannerapp.ui.weekPlanner.view;

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
import com.example.foodplannerapp.databinding.FragmentWeekPlannerBinding;
import com.example.foodplannerapp.models.ModelMeal;
import com.example.foodplannerapp.models.MySharedPref;
import com.example.foodplannerapp.models.WeekPlannerModel;
import com.example.foodplannerapp.ui.weekPlanner.presenter.WeekPlannerPresenter;
import com.example.foodplannerapp.ui.weekPlanner.presenter.WeekPlannerPresenterInterface;
import com.example.foodplannerapp.ui.weekPlanner.view.AdapterDays;
import com.example.foodplannerapp.ui.weekPlanner.view.AdapterMealOfTheDay;
import com.example.foodplannerapp.ui.weekPlanner.view.WeekPlannerFragment;
import com.example.foodplannerapp.utils.Constants;
import com.example.foodplannerapp.utils.Extensions;

import java.util.ArrayList;
import java.util.List;


public class WeekPlannerFragment extends Fragment implements WeekPlannerViewInterface {

    private FragmentWeekPlannerBinding binding;
    private ArrayList<String> list;

    private AdapterDays adapter;
    private AdapterMealOfTheDay adapterMealOfTheDay;

    private WeekPlannerPresenterInterface presenterInterface;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_week_planner, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentWeekPlannerBinding.bind(view);

        if (MySharedPref.getUserId().isEmpty()){
            Toast.makeText(requireContext(), getString(R.string.loginFirst), Toast.LENGTH_SHORT).show();
        }else {
            list = new ArrayList<>();
            adapter = new AdapterDays();
            adapterMealOfTheDay = new AdapterMealOfTheDay();
            presenterInterface = new WeekPlannerPresenter(this);
            inflateDaysRecycler();
            onClicks();
        }
    }

    private ArrayList<String> fillDayList() {
        list.add(getString(R.string.saturday));
        list.add(getString(R.string.sunday));
        list.add(getString(R.string.monday));
        list.add(getString(R.string.tuesday));
        list.add(getString(R.string.wednesday));
        list.add(getString(R.string.thursday));
        list.add(getString(R.string.friday));

        return list;
    }

    private void inflateDaysRecycler() {
        adapter.setList(fillDayList());
        binding.recyclerDays.setAdapter(adapter);
    }

    private void onClicks() {
        adapter.setOnDayClickListener(new AdapterDays.SetOnDayClickListener() {
            @Override
            public void onItemClick(String day) {

                    getData(day);

            }
        });
        adapterMealOfTheDay.setOnItemClickListener(new AdapterMealOfTheDay.SetOnItemClickListener() {
            @Override
            public void onDeleteClicked(WeekPlannerModel model) {
                showDialog(model);
            }

            @Override
            public void onItemClicked(WeekPlannerModel weekPlannerModel) {
                String mealId = weekPlannerModel.getIdMeal();
                WeekPlannerFragmentDirections.ActionWeekPlannerFragment2ToDetailsFragment2 action =
                        WeekPlannerFragmentDirections.actionWeekPlannerFragment2ToDetailsFragment2(mealId);
                Navigation.findNavController(requireView()).navigate(action);
            }
        });

        binding.btnBackInWeekFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view)
                        .popBackStack();
            }
        });
    }


    private void getData(String day) {
        presenterInterface.getAllMealByDay(day)
                .observe(getViewLifecycleOwner(), new Observer<List<WeekPlannerModel>>() {
                    @Override
                    public void onChanged(List<WeekPlannerModel> mealList) {
                        adapterMealOfTheDay.setList((ArrayList<WeekPlannerModel>) mealList);
                        binding.recyclerMeal.setAdapter(adapterMealOfTheDay);
                        adapterMealOfTheDay.notifyDataSetChanged();
                    }
                });
    }


    @Override
    public void deleteWeeklyMeal(WeekPlannerModel weekPlannerModel) {
        presenterInterface.deleteWeeklyMealFromFirebase(weekPlannerModel);
        presenterInterface.deleteWeeklyMeal(weekPlannerModel);
    }

    private void showDialog(WeekPlannerModel model) {
        Extensions.showConfirmationDialog(requireContext(), Constants.ANONYMOUS_USER_MESSAGE,
                //onYes
                () -> {
                    deleteWeeklyMeal(model);
                    getData(model.getDay());
                    Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_SHORT).show();
                },
                () -> {
                }
        );

    }

    @Override
    public void getError(String error) {
        Toast.makeText(requireContext(), "Fail :" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}