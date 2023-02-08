package com.example.foodplannerapp.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplannerapp.BottomNavigation;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.utils.MySharedPref;


public class SplashFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                action(view);
            }
        }, 3000);
    }

    private void action(View view) {
        if (MySharedPref.getUserId().isEmpty()) {
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_welcomeFragment);
        } else {
            Intent intent = new Intent(requireActivity(), BottomNavigation.class);
            startActivity(intent);
        }
    }

}