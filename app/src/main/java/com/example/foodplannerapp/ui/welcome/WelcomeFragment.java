package com.example.foodplannerapp.ui.welcome;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.databinding.FragmentWelcomeBinding;

public class WelcomeFragment extends Fragment {

    FragmentWelcomeBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWelcomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CreateLineUnderLogin();

    }

    private void CreateLineUnderLogin() {
        binding.textViewLogin.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}