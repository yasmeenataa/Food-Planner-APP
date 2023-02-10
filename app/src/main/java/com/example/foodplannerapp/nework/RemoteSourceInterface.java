package com.example.foodplannerapp.nework;

import com.example.foodplannerapp.models.ModelMealRoot;

import io.reactivex.rxjava3.core.Single;

public interface RemoteSourceInterface {

    void enqueueCall(NetworkDelegate networkDelegate);

    void enqueueCallCategory(NetworkDelegate networkDelegate);


    void enqueueCallCategoryItem(NetworkDelegateForCategory networkDelegate , String categoryName);

    Single<ModelMealRoot> getRandomMeal();

    Single<ModelMealRoot> getMealByd(String mealId);
    
}
