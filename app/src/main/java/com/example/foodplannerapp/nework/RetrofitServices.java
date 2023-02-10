package com.example.foodplannerapp.nework;

import com.example.foodplannerapp.models.CategoriesRootModel;
import com.example.foodplannerapp.models.ModelMealRoot;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitServices {
    @GET("random.php")
    Single<ModelMealRoot> getRandomMeal();


    @GET("categories.php")
    Single<CategoriesRootModel> getAllCategories();

    @GET("filter.php")
    Single<ModelMealRoot> getAllMealsByCategory(@Query("c") String category);

    @GET("lookup.php")
    Single<ModelMealRoot> getMealById(@Query("i") String mealId);



}
