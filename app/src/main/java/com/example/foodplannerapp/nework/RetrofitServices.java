package com.example.foodplannerapp.nework;

import com.example.foodplannerapp.models.AreaListRootModel;
import com.example.foodplannerapp.models.CategoriesRootModel;
import com.example.foodplannerapp.models.IngredientListRootModel;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

import com.example.foodplannerapp.models.CategoriesRootModel;
import com.example.foodplannerapp.models.ModelMealRoot;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitServices {
    @GET("list.php?a=list")
    Single<AreaListRootModel> getAreaList();
    @GET("list.php?i=list")
    Single<IngredientListRootModel> getIngredientList();

    @GET("random.php")
    Single<ModelMealRoot> getRandomMeal();


    @GET("categories.php")
    Single<CategoriesRootModel> getAllCategories();

    @GET("filter.php")
    Single<ModelMealRoot> getAllMealsByCategory(@Query("c") String category);

    @GET("lookup.php")
    Single<ModelMealRoot> getMealById(@Query("i") String mealId);



}
