package com.example.foodplannerapp.repo.mealRepo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodplannerapp.models.ModelMeal;
import com.example.foodplannerapp.models.ModelMealRoot;
import com.example.foodplannerapp.nework.NetworkDelegateForCategory;
import com.example.foodplannerapp.nework.RemoteSourceClient;
import com.example.foodplannerapp.nework.NetworkDelegate;
import com.example.foodplannerapp.nework.RemoteSourceInterface;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealRepo implements MealRepoInterface {

    private static RemoteSourceInterface remoteSourceInterface;
    private static MealRepo repo;

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
    }

    @Override
    public void getRandomMeal(NetworkDelegate networkDelegate) {
        remoteSourceInterface.enqueueCall(networkDelegate);
    }

    @Override
    public void getRandomMeal2() {
        remoteSourceInterface.getRandomMeal()
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ModelMealRoot>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ModelMealRoot modelMealRoot) {
                        listLiveData.setValue(modelMealRoot.getMeals());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        errorMessage.setValue(e.getMessage());

                    }
                });
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
                        Log.i("TAG", "onSuccess: "+modelMealRoot.getMeals().size());
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
    public void getMealsOfCategory(NetworkDelegateForCategory networkDelegateForCategory, String catName) {
        remoteSourceInterface.enqueueCallCategoryItem(networkDelegateForCategory, catName);
    }
}
