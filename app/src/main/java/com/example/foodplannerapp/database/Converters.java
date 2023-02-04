package com.example.foodplannerapp.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;

public class Converters {

    @TypeConverter
    // tae user and return String because of .toJson
    public static String fromArrayOfUserIdsToString(ArrayList<String> userIdList) {
        // .toJson return String
        return new Gson().toJson(userIdList);
    }

    @TypeConverter
    // take String and convert into User onc again
    public ArrayList<String> fromStringToArrayOfUserIds(String stringUserId) {
        Type listType = new TypeToken<ArrayList<String>>() {
        }.getType();
        return new Gson().fromJson(stringUserId, listType);
    }

}
