package com.example.foodplannerapp;

import static android.view.View.VISIBLE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.models.MySharedPref;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottom_navigation;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottom_navigation = findViewById(R.id.bottom_navigation);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottom_navigation, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if (navDestination.getId() == R.id.signUpFragment ||
                        navDestination.getId() == R.id.logInFragment ||
                        navDestination.getId() == R.id.welcomeFragment ||
                        navDestination.getId() == R.id.splashFragment ||
                        navDestination.getId() == R.id.detailsFragment ||
                        navDestination.getId() == R.id.allCategoriesFragment3) {
                    bottom_navigation.setVisibility(View.GONE);
                } else {
                    bottom_navigation.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}