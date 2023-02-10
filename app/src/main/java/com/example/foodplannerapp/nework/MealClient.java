package com.example.foodplannerapp.nework;

import com.example.foodplannerapp.models.AreaListRootModel;
import com.example.foodplannerapp.models.IngredientListRootModel;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealClient {
    private static  final String Base_URL="www.themealdb.com/api/json/v1/1/";
    private static MealClient client=null;

    public static MealClient getInstance(){
        if(client==null)
        {
            client=new MealClient();
        }
        return client;
    }
    private MealClient(){}

    public  void enqueueCall(NetworkDelegate networkDelegate) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        RetrofitServices service = retrofit.create(RetrofitServices.class);


        Single<AreaListRootModel> AreaListObservable = service.getAreaList();
        AreaListObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> networkDelegate.onSuccessAreaList(item.getMeals()),
                        error -> networkDelegate.onFailureAreaList("Error :" + error.toString())
                );

        Single<IngredientListRootModel> IngredientListObservable = service.getIngredientList();
        IngredientListObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> networkDelegate.onSuccessIngredientList(item.getMeals()),
                        error -> networkDelegate.onFailureIngredientList("Error :" + error.toString())
                );
    }

}
