package com.example.foodplannerapp.utils;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.models.MySharedPref;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class Extensions {


    public static void showProgressDialog(Context context, int delay, Runnable runnable) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
        builder.setCancelable(false);

        ProgressBar progressBar = new ProgressBar(context);
        TextView textView = new TextView(context);
        textView.setText("Login successfully, Please wait...");

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.addView(progressBar);
        linearLayout.addView(textView);

        builder.setView(linearLayout);
        final AlertDialog dialog = builder.create();
        dialog.show();

        new Handler().postDelayed(() -> {
            dialog.dismiss();
            runnable.run();
        }, delay);
    }


    public static void showProgressDialog2(Context context, int delay, Runnable runnable) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
        builder.setCancelable(false);

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_progress, null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();

        new Handler().postDelayed(() -> {
            dialog.dismiss();
            runnable.run();
        }, delay);
    }

    public static void showConfirmationDialog(Context context, String message, Runnable onYes, Runnable onNo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setPositiveButton("Yes", (dialog, id) -> onYes.run())
                .setNegativeButton("No", (dialog, id) -> onNo.run());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void clearAllDataFromSharedPref(){
        MySharedPref.setUserId(null);
        MySharedPref.setUserEmail(null);
        MySharedPref.setUserName(null);
        MySharedPref.setUserPassword(null);
    }


}
