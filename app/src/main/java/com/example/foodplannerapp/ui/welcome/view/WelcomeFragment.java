package com.example.foodplannerapp.ui.welcome.view;

import android.content.Intent;
import android.graphics.Paint;
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

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.databinding.FragmentWelcomeBinding;
import com.example.foodplannerapp.models.MySharedPref;
import com.example.foodplannerapp.ui.login.presenter.LoginPresenter;
import com.example.foodplannerapp.ui.login.presenter.PresenterInterface;
import com.example.foodplannerapp.ui.login.view.ViewInterface;
import com.example.foodplannerapp.utils.Constants;
import com.example.foodplannerapp.utils.Extensions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;

public class WelcomeFragment extends Fragment implements ViewInterface {

    FragmentWelcomeBinding binding;
    GoogleSignInClient client;

    private PresenterInterface presenterInterface;


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
        presenterInterface = new LoginPresenter(this);
        GoogleSignInOptions options = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();
        client = GoogleSignIn.getClient(getContext(), options);
        CreateLineUnderLogin();
        onClicks();

    }

    private void CreateLineUnderLogin() {
        binding.textViewLogin.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
    }


    private void onClicks() {

        binding.btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = client.getSignInIntent();
                startActivityForResult(i, 1234);
            }
        });

        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_signUpFragment2);
            }
        });

        binding.textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_logInFragment2);
            }
        });

        binding.btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUserAuthorization();
            }
        });


    }

    private void checkUserAuthorization() {
        if (MySharedPref.getUserId().isEmpty()) {
            Extensions.showConfirmationDialog(requireContext(), Constants.ANONYMOUS_USER_MESSAGE,
                    //onYes
                    () -> {
                        Log.i("TAG", "checkUserAuthorization: YES");
                        Extensions.clearAllDataFromSharedPref();
                        Navigation.findNavController(requireView())
                                .navigate(R.id.action_welcomeFragment_to_homeFragment2);
                    },
                    //onNo
                    () -> {
                        Log.i("TAG", "checkUserAuthorization: No");
                    }
            );
        } else {
            MySharedPref.getUserId();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(requireContext(), "Failed Check your Network", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                Toast.makeText(requireContext(), "Failed check Network", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                UserProfileChangeRequest user = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(account.getDisplayName()).build();
                                authResult.getUser().updateProfile(user);
                                MySharedPref.setUserId(authResult.getUser().getUid());
                                MySharedPref.setUserEmail(authResult.getUser().getEmail());
                                MySharedPref.setUserName(authResult.getUser().getDisplayName());
                                MySharedPref.setUserUriKey(String.valueOf(authResult.getUser().getPhotoUrl()));
                                if (task.isSuccessful()) {
                                    Extensions.showProgressDialog2(requireContext(), 3000, () -> {
                                        presenterInterface.getFavData();
                                        presenterInterface.getPlanData();
                                        Navigation.findNavController(requireView())
                                                .navigate(R.id.action_welcomeFragment_to_homeFragment2);
                                    });
                                } else
                                    Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });

            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onLoginSuccess(String userId) {
//        Extensions.showProgressDialog2(requireContext(), 3000, () -> {
//            presenterInterface.getFavData();
//            presenterInterface.getPlanData();
//            Navigation.findNavController(requireView())
//                    .navigate(R.id.action_welcomeFragment_to_homeFragment2);
//        });
    }

    @Override
    public void onLoginFail(String message) {
//        Toast.makeText(requireContext(), "Fail :" + message, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//
//            Navigation.findNavController(requireView())
//                    .navigate(R.id.action_welcomeFragment_to_homeFragment);
//        }
//    }
}