package com.example.foodplannerapp.repo.authRepo;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.foodplannerapp.models.MySharedPref;
import com.example.foodplannerapp.nework.NetworkDelegate;
import com.example.foodplannerapp.utils.FirebaseOnCompleteListener;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;

public class Repo implements RepoInterface {

    private final FirebaseAuth auth;

    private final FirebaseOnCompleteListener firebaseOnCompleteListener;

    public Repo(FirebaseOnCompleteListener firebaseOnCompleteListener) {
        this.auth = FirebaseAuth.getInstance();
        this.firebaseOnCompleteListener = firebaseOnCompleteListener;
    }

    @Override
    public void signUp(String email, String pass) {

        auth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(MySharedPref.getUserName()).build();
                            task.getResult().getUser().updateProfile(userProfileChangeRequest);
                            MySharedPref.setUserEmail(task.getResult().getUser().getEmail());
                            MySharedPref.setUserUriKey(String.valueOf(task.getResult().getUser().getPhotoUrl()));
                            firebaseOnCompleteListener.onSuccess(task.getResult().getUser().getUid());
                        } else {
                            firebaseOnCompleteListener.onFail(task.getException().getMessage());
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        firebaseOnCompleteListener.onFail(e.getMessage());
                    }
                }).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        firebaseOnCompleteListener.onFail("Server is Down or Having Problem With Internet");
                    }
                });

    }

    @Override
    public void login(String email, String pass) {

        auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            MySharedPref.setUserName(task.getResult().getUser().getDisplayName());
                            MySharedPref.setUserEmail(task.getResult().getUser().getEmail());
                            MySharedPref.setUserUriKey(String.valueOf(task.getResult().getUser().getPhotoUrl()));
                            firebaseOnCompleteListener.onSuccess(task.getResult().getUser().getUid());
                        } else {
                            firebaseOnCompleteListener.onFail(task.getException().getMessage());
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        firebaseOnCompleteListener.onFail(e.getMessage());
                    }
                }).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        firebaseOnCompleteListener.onFail("Server is Down or Having Problem With Internet");
                    }
                });
    }

    @Override
    public void logout() {
        auth.signOut();
    }


}
