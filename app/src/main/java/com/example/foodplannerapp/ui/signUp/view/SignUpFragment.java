package com.example.foodplannerapp.ui.signUp.view;

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
import com.example.foodplannerapp.databinding.FragmentSignUpBinding;
import com.example.foodplannerapp.models.MySharedPref;
import com.example.foodplannerapp.ui.signUp.presenter.PresenterInterface;
import com.example.foodplannerapp.ui.signUp.presenter.SignUpPresenter;


public class SignUpFragment extends Fragment implements ViewInterface {

    private FragmentSignUpBinding binding;


    private PresenterInterface presenterInterface;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenterInterface = new SignUpPresenter(this);

        onClicks();

    }


    private void onClicks() {
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getData();
            }
        });

        binding.textViewHaveAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_logInFragment);
            }
        });

        binding.imgBackSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_logInFragment);
            }
        });
    }

    private void getData() {
        String name = binding.editUserName.getText().toString().trim();
        String pass = binding.PasswordEdit.getText().toString().trim();
        String email = binding.editEmail.getText().toString().trim();

        validation(name, email, pass);


    }

    private void validation(String name, String email, String pass) {
        if (name.isEmpty()) {
            binding.editUserName.setError("Required");
        } else if (email.isEmpty()) {
            binding.editEmail.setError("Required");
        } else if (pass.isEmpty()) {
            binding.PasswordEdit.setError("Required");
        } else {
            MySharedPref.setUserName(name);
            MySharedPref.setUserPassword(pass);
            presenterInterface.signUp(email, pass);

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onRegisterSuccess(String userId) {

        Navigation.findNavController(requireView())
                .navigate(R.id.action_signUpFragment_to_homeFragment);

    }

    @Override
    public void onRegisterFail(String message) {

        Toast.makeText(requireActivity().getBaseContext(), "Fail" + message, Toast.LENGTH_SHORT).show();

    }
}