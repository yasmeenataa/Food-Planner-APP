package com.example.foodplannerapp.ui.favourite.presenter;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodplannerapp.models.ModelMeal;
import com.example.foodplannerapp.models.MySharedPref;
import com.example.foodplannerapp.repo.mealRepo.MealRepo;
import com.example.foodplannerapp.ui.favourite.view.FavViewInterface;
import com.example.foodplannerapp.utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavMealPresenter implements FavMealPresenterInterface {


    private MealRepo repo = null;
    private FavViewInterface viewInterface;

    private DatabaseReference reference;

    private MutableLiveData<Integer> progressLiveData;


    public FavMealPresenter(FavViewInterface favViewInterface) {
        this.viewInterface = favViewInterface;
        repo = MealRepo.getMealRepoInstance();
        reference = FirebaseDatabase.getInstance().getReference();
        progressLiveData = new MutableLiveData<>();
    }

    @Override
    public LiveData<List<ModelMeal>> getAllMeal() {
        return repo.getFavMeals();
    }

    @Override
    public void deleteFavMeal(ModelMeal modelMeal) {
        progressLiveData.setValue(View.VISIBLE);
        repo.deleteMeal(modelMeal).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
//                        repo.getDisposable().add(d);
                    }

                    @Override
                    public void onComplete() {
                        progressLiveData.setValue(View.GONE);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        progressLiveData.setValue(View.GONE);
                    }
                });
    }

    @Override
    public void deleteFavMealFromFireBase(ModelMeal modelMeal) {
        progressLiveData.setValue(View.VISIBLE);
        reference.child(MySharedPref.getUserId())
                .child(Constants.FAV_REF)
                .child(modelMeal.getIdMeal())
                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@androidx.annotation.NonNull Task<Void> task) {
                        progressLiveData.setValue(View.GONE);

                    }
                }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressLiveData.setValue(View.GONE);
                        deleteFavMeal(modelMeal);
                    }
                });
    }

    @Override
    public CompositeDisposable getDisposable() {
        return repo.getDisposable();
    }

    @Override
    public MutableLiveData<Integer> getProgressLiveData() {
        return progressLiveData;
    }
}
