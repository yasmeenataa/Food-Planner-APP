package com.example.foodplannerapp.nework;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.example.foodplannerapp.models.CategoriesRootModel;
import com.example.foodplannerapp.models.ModelMealRoot;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RemoteSourceClient implements RemoteSourceInterface {

    private static RemoteSourceClient mealClient = null;

    private MutableLiveData<Integer> progressBarLiveData;

    @Override
    public MutableLiveData<Integer> getProgressBarLiveData() {
        return progressBarLiveData;
    }

    private RemoteSourceClient() {
        progressBarLiveData = new MutableLiveData<>();
    }

    public static RemoteSourceClient getMealClientInstance() {
        if (mealClient == null) {
            mealClient = new RemoteSourceClient();

        }
        return mealClient;
    }


    @Override
    public void enqueueCall(NetworkDelegate networkDelegate) {
        progressBarLiveData.setValue(View.VISIBLE);
        RetrofitConnection
                .getServices()
                .getRandomMeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ModelMealRoot>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ModelMealRoot modelMealRoot) {
                        networkDelegate.onSuccessfulResult(modelMealRoot.getMeals());
                        progressBarLiveData.setValue(View.GONE);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        progressBarLiveData.setValue(View.GONE);
                        networkDelegate.onFailureResult(e.getLocalizedMessage());
                    }
                });

    }

    @Override
    public void enqueueCallCategory(NetworkDelegate networkDelegate) {
        progressBarLiveData.setValue(View.VISIBLE);
        RetrofitConnection
                .getServices()
                .getAllCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CategoriesRootModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull CategoriesRootModel categoriesRootModel) {
                        networkDelegate.onCategorySuccessfulResult(categoriesRootModel.getCategories());
                        progressBarLiveData.setValue(View.GONE);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        networkDelegate.onFailureResult(e.getMessage());
                        progressBarLiveData.setValue(View.GONE);
                    }
                });

    }

    @Override
    public void enqueueCallArea(NetworkDelegateForArea networkDelegate) {
        RetrofitConnection
                .getServices()
                .getAreaList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> networkDelegate.onSuccessAreaList(item.getMeals()),
                        error -> networkDelegate.onFailureAreaList("Error :" + error.toString())
                );
    }

    @Override
    public void enqueueCallIngredients(NetworkDelegateForIngredient networkDelegate) {
        RetrofitConnection
                .getServices()
                .getIngredientList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> networkDelegate.onSuccessIngredientList(item.getMeals()),
                        error -> networkDelegate.onFailureIngredientList("Error :" + error.toString())
                );
    }


    @Override
    public void enqueueCallCategoryItem(NetworkDelegateForCategory networkDelegate, String categoryName) {
        progressBarLiveData.setValue(View.VISIBLE);
        RetrofitConnection
                .getServices()
                .getAllMealsByCategory(categoryName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ModelMealRoot>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ModelMealRoot modelMealRoot) {
                        networkDelegate.onCategoryNameSuccessfulResult(modelMealRoot.getMeals());
                        progressBarLiveData.setValue(View.GONE);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        networkDelegate.onFailureResult(e.getMessage());
                        progressBarLiveData.setValue(View.GONE);

                    }
                });
    }

    @Override
    public void enqueueCallAreaItem(NetworkDelegateForAreaItem networkDelegateForArea, String areaName) {
        RetrofitConnection
                .getServices()
                .getAllMealsByArea(areaName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> networkDelegateForArea.onAreaNameSuccessfulResult(item.getMeals()),
                        error -> networkDelegateForArea.onFailureAreaNameResult("Error :" + error.toString())
                );
    }

    @Override
    public void enqueueIngredientItem(NetworkDelegateForIngredientItem delegateForIngredient, String ingredientName) {
        RetrofitConnection
                .getServices()
                .getAllMealsByIngredient(ingredientName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> delegateForIngredient.onIngredientNameSuccessfulResult(item.getMeals()),
                        error -> delegateForIngredient.onFailureIngredientNameResult("Error :" + error.toString())
                );
    }


    @Override
    public Single<ModelMealRoot> getRandomMeal() {
        return RetrofitConnection.getServices().getRandomMeal();
    }

    @Override
    public Single<ModelMealRoot> getMealByd(String mealId) {
        return RetrofitConnection.getServices().getMealById(mealId);
    }

    @Override
    public void enqueueCallSearchMealByName(NetworkDelegateForSearchMeal delegateForSearchMeal, String mealName) {
        RetrofitConnection
                .getServices()
                .searchMealByName(mealName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> delegateForSearchMeal.onSuccessSearchMealByName(item.getMeals()),
                        error -> delegateForSearchMeal.onFailureSearchMealByName("Error :" + error.toString())
                );
    }


}
