package com.example.foodplannerapp.ui.profile;

import android.content.Intent;
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
import com.example.foodplannerapp.databinding.FragmentProfileBinding;
import com.example.foodplannerapp.MainActivity;
import com.example.foodplannerapp.models.MySharedPref;
import com.example.foodplannerapp.repo.authRepo.Repo;

public class ProfileFragment extends Fragment implements ProfileViewInterface {

    private FragmentProfileBinding binding;
    private ProfilePresenterInterface presenterInterface;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenterInterface = new ProfilePresenter(this);

        onClicks();


    }

    private void onClicks() {
        binding.textViewFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_favouriteFragment);
            }
        });

        binding.textViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MySharedPref.setUserId("");
                MySharedPref.setUserPassword("");
                MySharedPref.setUserName("");
                MySharedPref.setUserEmail("");
                onLogoutSuccess();
                Navigation.findNavController(view)
                        .navigate(R.id.action_profileFragment_to_welcomeFragment);
            }
        });

        binding.textViewWeeklyPlannerMeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view)
                        .navigate(R.id.action_profileFragment_to_weekPlannerFragment2);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.getRoot();
    }

    @Override
    public void onLogoutSuccess() {
        presenterInterface.logoutFromApp();
    }

    @Override
    public void onLogoutFail(String message) {
        Toast.makeText(requireContext(), "Fail Logout : " + message, Toast.LENGTH_SHORT).show();
    }
}