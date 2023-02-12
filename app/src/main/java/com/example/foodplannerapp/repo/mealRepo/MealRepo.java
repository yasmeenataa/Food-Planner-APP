package com.example.foodplannerapp.repo.mealRepo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodplannerapp.database.LocalSource;
import com.example.foodplannerapp.database.LocalSourceInterface;
import com.example.foodplannerapp.models.ModelMeal;
import com.example.foodplannerapp.models.ModelMealRoot;
import com.example.foodplannerapp.nework.NetworkDelegateForArea;
import com.example.foodplannerapp.nework.NetworkDelegateForAreaItem;
import com.example.foodplannerapp.models.WeekPlannerModel;
import com.example.foodplannerapp.nework.NetworkDelegateForCategory;
import com.example.foodplannerapp.nework.NetworkDelegateForIngredient;
import com.example.foodplannerapp.nework.NetworkDelegateForIngredientItem;
import com.example.foodplannerapp.nework.NetworkDelegateForSearchMeal;
import com.example.foodplannerapp.nework.RemoteSourceClient;
import com.example.foodplannerapp.nework.NetworkDelegate;
import com.example.foodplannerapp.nework.RemoteSourceInterface;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealRepo implements MealRepoInterface {

    private static RemoteSourceInterface remoteSourceInterface;
    private static MealRepo repo;


    private static LocalSourceInterface localSourceInterface;

    private static MutableLiveData<ArrayList<ModelMeal>> listLiveData;
    private static MutableLiveData<String> errorMessage;

    public MutableLiveData<ArrayList<ModelMeal>> getListLiveData() {
        return listLiveData;
    }

    public static synchronized MealRepo getMealRepoInstance() {
        if (repo == null) {
            repo = new MealRepo();
        }
        return repo;
    }


    private MealRepo() {
        listLiveData = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
        remoteSourceInterface = RemoteSourceClient.getMealClientInstance();
        localSourceInterface = LocalSource.getLocalInstance();
    }

    @Override
    public void getRandomMeal(NetworkDelegate networkDelegate) {
        remoteSourceInterface.enqueueCall(networkDelegate);
    }


    @Override
    public void getMealById(String mealId) {
        remoteSourceInterface
                .getMealByd(mealId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ModelMealRoot>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ModelMealRoot modelMealRoot) {
                        Log.i("TAG", "onSuccess: " + modelMealRoot.getMeals().size());
                        listLiveData.setValue(modelMealRoot.getMeals());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        errorMessage.setValue(e.getMessage());
                    }
                });

    }


    @Override
    public void getAllCategory(NetworkDelegate networkDelegate) {
        remoteSourceInterface.enqueueCallCategory(networkDelegate);
    }

    @Override
    public void getAllArea(NetworkDelegateForArea delegateForArea) {
        remoteSourceInterface.enqueueCallArea(delegateForArea);
    }

    @Override
    public void getAllIngredient(NetworkDelegateForIngredient delegateForIngredient) {
        remoteSourceInterface.enqueueCallIngredients(delegateForIngredient);
    }

    @Override
    public void getMealsOfCategory(NetworkDelegateForCategory networkDelegateForCategory, String catName) {
        remoteSourceInterface.enqueueCallCategoryItem(networkDelegateForCategory, catName);
    }

    @Override
    public Completable insertMeal(ModelMeal modelMeal) {
        return localSourceInterface.insertMeal(modelMeal);

    }

    @Override
    public Completable deleteMeal(ModelMeal modelMeal) {
        return localSourceInterface.removeMeal(modelMeal);
    }

    @Override
    public LiveData<List<ModelMeal>> getFavMeals() {
        return localSourceInterface.getFavProduct();
    }

    @Override
    public LiveData<List<WeekPlannerModel>> getPlannerMeals(String day) {
        return localSourceInterface.getWeeKMeals(day);
    }

    @Override
    public Completable insertWeekMeal(WeekPlannerModel weekPlannerModel) {
        return localSourceInterface.insertWeekPlannerMeal(weekPlannerModel);

    }

    @Override
    public Completable deleteWeekMeal(WeekPlannerModel weekPlannerModel) {
        return localSourceInterface
                .removeWeekPlannerMeal(weekPlannerModel);

    }

    @Override
    public Completable deleteFavTableRoom() {
        return localSourceInterface
                .deleteFavTableRoom();
    }

    @Override
    public Completable deleteWeekTableRoom() {
        return localSourceInterface
                .deleteWeekTableRoom();
    }

    @Override
    public Single<ModelMeal> isFav(String mealId) {
        return localSourceInterface.isFav(mealId);
    }

    @Override
    public void getMealByName(NetworkDelegateForSearchMeal delegateForSearchMeal, String mealName) {
        remoteSourceInterface.enqueueCallSearchMealByName(delegateForSearchMeal, mealName);
    }

    @Override
    public MutableLiveData<Integer> getProgressLiveData() {
        return remoteSourceInterface.getProgressBarLiveData();
    }

    @Override
    public void getMealsOfArea(NetworkDelegateForAreaItem delegateForAreaItem, String areaName) {
        remoteSourceInterface.enqueueCallAreaItem(delegateForAreaItem, areaName);
    }

    @Override
    public void getMealsOfIngredient(NetworkDelegateForIngredientItem delegateForIngredientItem, String ingredientName) {
        remoteSourceInterface.enqueueIngredientItem(delegateForIngredientItem, ingredientName);
    }
}
