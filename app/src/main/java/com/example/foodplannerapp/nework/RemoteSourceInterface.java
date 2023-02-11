package com.example.foodplannerapp.nework;

import com.example.foodplannerapp.models.ModelMealRoot;

import io.reactivex.rxjava3.core.Single;

public interface RemoteSourceInterface {

    void enqueueCall(NetworkDelegate networkDelegate);

    void enqueueCallCategory(NetworkDelegate networkDelegate);
    void enqueueCallArea(NetworkDelegateForArea networkDelegate);
    void enqueueCallIngredients(NetworkDelegateForIngredient networkDelegate);

    void enqueueCallCategoryItem(NetworkDelegateForCategory networkDelegate , String categoryName);
    void enqueueCallAreaItem(NetworkDelegateForAreaItem networkDelegateForArea,String areaName);
    void enqueueIngredientItem(NetworkDelegateForIngredientItem delegateForIngredient,String ingredientName);

    Single<ModelMealRoot> getRandomMeal();

    Single<ModelMealRoot> getMealByd(String mealId);

    
}
