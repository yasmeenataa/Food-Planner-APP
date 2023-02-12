package com.example.foodplannerapp.ui.weekPlanner.presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplannerapp.models.MySharedPref;
import com.example.foodplannerapp.models.WeekPlannerModel;
import com.example.foodplannerapp.repo.mealRepo.MealRepo;
import com.example.foodplannerapp.ui.weekPlanner.view.WeekPlannerViewInterface;
import com.example.foodplannerapp.utils.Constants;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class WeekPlannerPresenter implements WeekPlannerPresenterInterface {


    private MealRepo repo;
    private WeekPlannerViewInterface viewInterface;

    private DatabaseReference ref;

    public WeekPlannerPresenter(WeekPlannerViewInterface viewInterface) {
        this.viewInterface = viewInterface;
        repo = MealRepo.getMealRepoInstance();
        ref = FirebaseDatabase.getInstance().getReference();
    }


    @Override
    public LiveData<List<WeekPlannerModel>> getAllMealByDay(String day) {
        return repo.getPlannerMeals(day);
    }

    @Override
    public void deleteWeeklyMeal(WeekPlannerModel weekPlannerModel) {
        repo.deleteWeekMeal(weekPlannerModel).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        viewInterface.getError(e.getLocalizedMessage());
                    }
                });
    }

    @Override
    public void deleteWeeklyMealFromFirebase(WeekPlannerModel weekPlannerModel) {
        ref.child(MySharedPref.getUserId())
                .child(Constants.WEEK_REF)
                .child(weekPlannerModel.getDay().concat("_").concat(weekPlannerModel.getIdMeal()))
                .removeValue();
    }
}
