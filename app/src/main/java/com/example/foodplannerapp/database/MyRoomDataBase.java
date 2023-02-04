package com.example.foodplannerapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.foodplannerapp.models.MealModel;
import com.example.foodplannerapp.utils.Constants;

@Database(entities = {MealModel.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class MyRoomDataBase extends RoomDatabase {

    private static MyRoomDataBase myRoomDataBase = null;

    public abstract MealDao getDao();

    public static synchronized MyRoomDataBase initRoom(Context context) {
        if (myRoomDataBase == null) {
            myRoomDataBase = Room.
                    databaseBuilder(context, MyRoomDataBase.class, Constants.MEAL_DATABASE)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return myRoomDataBase;

    }

    public MyRoomDataBase getInstance(){
        return  myRoomDataBase;
    }


}
