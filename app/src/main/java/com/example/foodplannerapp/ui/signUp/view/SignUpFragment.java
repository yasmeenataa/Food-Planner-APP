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

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUpFragment extends Fragment implements ViewInterface {

    private FragmentSignUpBinding binding;


    private PresenterInterface presenterInterface;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
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
        binding.btnSignUp.setOnClickListener(view -> getData());

        binding.textViewHaveAcount.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_logInFragment));

        binding.imgBackSignUp.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_logInFragment));
    }

    private void getData() {
        String name = binding.editUserName.getText().toString().trim();
        String pass = binding.PasswordEdit.getText().toString().trim();
        String email = binding.editEmail.getText().toString().trim();

        validation(name, email, pass);


    }

    private void validation(String name, String email, String pass) {
        binding.inputEmail.setError(null);
        binding.inputUserName.setError(null);
        binding.passwordLayout.setError(null);
        if (name.isEmpty()) {
            binding.inputUserName.setError("Required");
        } else if (email.isEmpty()) {
            binding.inputEmail.setError("Required");
        } else if (!isValidEmail(email)) {
            binding.inputEmail.setError("Invalid email");
        } else if (pass.isEmpty()) {
            binding.passwordLayout.setError("Required");

        }else if(!isValidPassword(pass))
        {
            binding.passwordLayout.setError("Password must have at least one numeric character ,one uppercase character,one lowercase character,one special symbol among @#$% and Password length should be between 8 and 20.");
        }
        else {
            MySharedPref.setUserName(name);
            MySharedPref.setUserPassword(pass);
            presenterInterface.signUp(email, pass);

        }
    }
    public  boolean isValidPassword(String password)
    {
        String regex= "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
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
    public void onRegisterSuccess(String userId) {

        Navigation.findNavController(requireView())
                .navigate(R.id.action_signUpFragment_to_homeFragment);

    }

    @Override
    public void onRegisterFail(String message) {

        Toast.makeText(requireActivity().getBaseContext(), "Fail" + message, Toast.LENGTH_SHORT).show();

    }
}