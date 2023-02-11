package com.example.foodplannerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.foodplannerapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class BottomNavigation extends AppCompatActivity {


//    private BottomNavigationView bottom_navigation;
//    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

//        bottom_navigation = findViewById(R.id.bottom_navigation);
//        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupWithNavController(bottom_navigation, navController);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}