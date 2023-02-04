package com.example.foodplannerapp;

import android.app.Application;

import com.example.foodplannerapp.database.MyRoomDataBase;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MyRoomDataBase.initRoom(this);
    }
}
