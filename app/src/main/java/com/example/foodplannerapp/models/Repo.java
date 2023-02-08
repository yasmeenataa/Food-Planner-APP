package com.example.foodplannerapp.models;

import androidx.annotation.NonNull;

import com.example.foodplannerapp.utils.MySharedPref;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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
                            firebaseOnCompleteListener.onSuccess(task.getResult().getUser().getUid());
                        } else {
                            firebaseOnCompleteListener.onFail(task.getException().getMessage());
                        }
                    }
                });

    }

    @Override
    public void login(String email, String pass) {
        auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        firebaseOnCompleteListener.onSuccess(task.getResult().getUser().getUid());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        firebaseOnCompleteListener.onFail(e.getMessage());
                    }
                });
    }

    @Override
    public void logout() {
        auth.signOut();
    }


}
