package com.example.foodplannerapp.nework;

import com.example.foodplannerapp.models.AreaListRootModel;
import com.example.foodplannerapp.models.CategoriesRootModel;
import com.example.foodplannerapp.models.IngredientListRootModel;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface RetrofitServices {
    @GET("list.php?a=list")
    Single<AreaListRootModel> getAreaList();
    @GET("list.php?i=list")
    Single<IngredientListRootModel> getIngredientList();

}
