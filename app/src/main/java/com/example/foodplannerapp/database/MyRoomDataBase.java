package com.example.foodplannerapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.foodplannerapp.models.ModelMeal;
import com.example.foodplannerapp.models.WeekPlannerModel;
import com.example.foodplannerapp.utils.Constants;

@Database(entities = {ModelMeal.class, WeekPlannerModel.class}, version = 2, exportSchema = false)
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

    public static MyRoomDataBase getInstance() {
        return myRoomDataBase;
    }


}
