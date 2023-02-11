package com.example.foodplannerapp.nework;

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

    private RemoteSourceClient() {

    }

    public static RemoteSourceClient getMealClientInstance() {
        if (mealClient == null) {
            mealClient = new RemoteSourceClient();

        }
        return mealClient;
    }



    @Override
    public void enqueueCall(NetworkDelegate networkDelegate) {
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
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        networkDelegate.onFailureResult(e.getLocalizedMessage());
                    }
                });

    }

    @Override
    public void enqueueCallCategory(NetworkDelegate networkDelegate) {
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
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        networkDelegate.onFailureResult(e.getMessage());
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
                        item ->networkDelegate.onSuccessIngredientList(item.getMeals()),
                        error -> networkDelegate.onFailureIngredientList("Error :" + error.toString())
                );
    }


    @Override
    public void enqueueCallCategoryItem(NetworkDelegateForCategory networkDelegate, String categoryName) {
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
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        networkDelegate.onFailureResult(e.getMessage());

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


}
