package com.example.foodplannerapp.database;

import androidx.lifecycle.LiveData;

import com.example.foodplannerapp.models.ModelMeal;
import com.example.foodplannerapp.models.WeekPlannerModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LocalSource implements LocalSourceInterface {

    private final MealDao mealDao;

    private static LocalSource localSource;

    public static synchronized LocalSource getLocalInstance() {
        if (localSource == null) {
            localSource = new LocalSource();
        }
        return localSource;
    }


    private LocalSource() {
        MyRoomDataBase myRoomDataBase = MyRoomDataBase.getInstance();
        mealDao = myRoomDataBase.getDao();
    }

    @Override
    public Completable insertMeal(ModelMeal modelMeal) {
        return mealDao.insertMeal(modelMeal);
    }

    @Override
    public Completable removeMeal(ModelMeal modelMeal) {
        return mealDao.deleteMeal(modelMeal);
    }

    @Override
    public LiveData<List<ModelMeal>> getFavProduct() {
        return mealDao.getFavProduct();
    }

    @Override
    public LiveData<List<WeekPlannerModel>> getWeeKMeals(String day) {
        return mealDao.getWeekPlannerMeal(day);
    }

    @Override
    public Completable insertWeekPlannerMeal(WeekPlannerModel weekPlannerModel) {
        return mealDao.insertWeekPlannerMeal(weekPlannerModel);
    }

    @Override
    public Completable removeWeekPlannerMeal(WeekPlannerModel weekPlannerModel) {
        return mealDao.deleteWeekPlannerMeal(weekPlannerModel);
    }

    @Override
    public Completable deleteFavTableRoom() {
        return mealDao.deleteFavTableRoom();
    }

    @Override
    public Completable deleteWeekTableRoom() {
        return mealDao.deleteWeekTableRoom();
    }

    @Override
    public Single<ModelMeal> isFav(String mealId) {
        return mealDao.isFav(mealId);
    }
}
