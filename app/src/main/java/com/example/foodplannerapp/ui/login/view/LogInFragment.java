package com.example.foodplannerapp.ui.login.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.databinding.FragmentLogInBinding;
import com.example.foodplannerapp.models.MySharedPref;
import com.example.foodplannerapp.ui.login.presenter.LoginPresenter;
import com.example.foodplannerapp.ui.login.presenter.PresenterInterface;
import com.example.foodplannerapp.utils.Extensions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LogInFragment extends Fragment implements ViewInterface {

    private FragmentLogInBinding binding;
    private PresenterInterface presenterInterface;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLogInBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenterInterface = new LoginPresenter(this);
        onClicks();


    }

    private void onClicks() {
        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });

        binding.imgBackSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_logInFragment_to_welcomeFragment);
            }
        });

        binding.txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_logInFragment_to_signUpFragment2);
            }
        });
    }

    private void getData() {
        String pass = binding.PasswordEdit.getText().toString().trim();
        String email = binding.UserNameEdit.getText().toString().trim();

        validation(email, pass);


    }

    private void validation(String email, String pass) {
        binding.UserNameLayout.setError(null);
        binding.passwordLayout.setError(null);
        if (email.isEmpty()) {
            binding.UserNameLayout.setError("Required");
        } else if (!isValidEmail(email)) {
            binding.UserNameLayout.setError("Invalid email");
        }else if (pass.isEmpty()) {
            binding.passwordLayout.setError("Required");
        } else {
            presenterInterface.login(email, pass);
        }
    }


    public  boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onLoginSuccess(String userId) {

        Extensions.showProgressDialog2(requireContext(), 3000, () -> {
            presenterInterface.getFavData();
            presenterInterface.getPlanData();
            Navigation.findNavController(requireView())
                    .navigate(R.id.action_logInFragment_to_homeFragment3);
        });

    }

    @Override
    public void onLoginFail(String message) {
        Toast.makeText(requireContext(), "Fail Check Network" , Toast.LENGTH_SHORT).show();
    }
}