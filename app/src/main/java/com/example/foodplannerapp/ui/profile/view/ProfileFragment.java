package com.example.foodplannerapp.ui.profile.view;

import android.graphics.Paint;
import android.net.Uri;
import android.os.Binder;
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

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.databinding.FragmentProfileBinding;
import com.example.foodplannerapp.models.MySharedPref;
import com.example.foodplannerapp.ui.profile.presenter.ProfilePresenter;
import com.example.foodplannerapp.ui.profile.presenter.ProfileViewInterface;
import com.example.foodplannerapp.utils.Constants;
import com.example.foodplannerapp.utils.Extensions;

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

        if (MySharedPref.getUserId().isEmpty()) {
            binding.textViewFav.setVisibility(View.GONE);
            binding.textViewLogout.setVisibility(View.GONE);
            binding.textViewWeeklyPlannerMeals.setVisibility(View.GONE);
            binding.textViewNameProfile.setVisibility(View.GONE);
            binding.imageViewFav.setVisibility(View.GONE);
            binding.imageViewLogout.setVisibility(View.GONE);
            binding.imageViewProfile.setVisibility(View.GONE);
            binding.imageViewSettings.setVisibility(View.GONE);
            binding.wave.setVisibility(View.GONE);
            binding.wave2.setVisibility(View.GONE);
            binding.card.setVisibility(View.GONE);
            binding.textViewLoginFirst.setVisibility(View.VISIBLE);
            CreateLineUnderLogin();
            binding.textViewLoginFirst.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view)
                            .navigate(R.id.action_profileFragment_to_welcomeFragment);
                }
            });
        } else {
            Log.i("TAG", MySharedPref.getUserName());
            binding.textViewNameProfile.setVisibility(View.VISIBLE);
            binding.textViewNameProfile.setText(MySharedPref.getUserName());
            binding.imageViewProfile.setVisibility(View.VISIBLE);
            binding.textViewLoginFirst.setVisibility(View.GONE);
            binding.imageViewProfile.setVisibility(View.VISIBLE);
            Glide.with(requireActivity().getBaseContext())
                    .load(MySharedPref.getUserUriKey())
                    .placeholder(R.drawable.baseline_person_24)
                    .error(R.drawable.baseline_person_24)
                    .into(binding.imageViewProfile);
            presenterInterface = new ProfilePresenter(this);
            onClicks();
        }


    }

    private void CreateLineUnderLogin() {
        binding.textViewLoginFirst.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
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
                showDialog(view);
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

    private void showDialog(View view) {
        Extensions.showConfirmationDialog(requireContext(), Constants.LOGOUT,
                //onYes
                () -> {
                    Extensions.clearAllDataFromSharedPref();
                    onLogoutSuccess();
                    Navigation.findNavController(view)
                            .navigate(R.id.action_profileFragment_to_welcomeFragment);
                },
                //onNo
                () -> {
                }
        );

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