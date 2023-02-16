package com.example.foodplannerapp.ui.dayFragment.presenter;

import androidx.lifecycle.MutableLiveData;

import com.example.foodplannerapp.models.MySharedPref;
import com.example.foodplannerapp.models.WeekPlannerModel;
import com.example.foodplannerapp.repo.mealRepo.MealRepo;
import com.example.foodplannerapp.ui.dayFragment.view.DayViewInterface;
import com.example.foodplannerapp.utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DayPresenter implements DayPresenterInterface {

    private DayViewInterface viewInterface;

    private DatabaseReference reference;

    private MealRepo repo;


    public DayPresenter(DayViewInterface viewInterface) {
        this.viewInterface = viewInterface;
        repo = MealRepo.getMealRepoInstance();
        reference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void insertMealToDay(WeekPlannerModel weekPlannerModel) {
        repo.insertWeekMeal(weekPlannerModel).subscribeOn(Schedulers.io())
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
                        viewInterface.getErrorMessage(e.getMessage());
                    }
                });
    }

    @Override
    public void insertMealToDayToFirebase(WeekPlannerModel weekPlannerModel) {
        reference.child(MySharedPref.getUserId())
                .child(Constants.WEEK_REF)
                .child(weekPlannerModel.getDay().concat("_").concat(weekPlannerModel.getIdMeal()))
                .setValue(weekPlannerModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@androidx.annotation.NonNull Task<Void> task) {

                    }
                }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        insertMealToDay(weekPlannerModel);
                    }
                });
    }
}
