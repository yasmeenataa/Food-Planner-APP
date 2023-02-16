package com.example.foodplannerapp.ui.favourite.presenter;

import androidx.lifecycle.LiveData;

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
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavMealPresenter implements FavMealPresenterInterface {


    private MealRepo repo = null;
    private FavViewInterface viewInterface;

    private DatabaseReference reference;


    public FavMealPresenter(FavViewInterface favViewInterface) {
        this.viewInterface = favViewInterface;
        repo = MealRepo.getMealRepoInstance();
        reference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public LiveData<List<ModelMeal>> getAllMeal() {
        return repo.getFavMeals();
    }

    @Override
    public void deleteFavMeal(ModelMeal modelMeal) {
        repo.deleteMeal(modelMeal).subscribeOn(Schedulers.io())
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

                    }
                });
    }

    @Override
    public void deleteFavMealFromFireBase(ModelMeal modelMeal) {
        reference.child(MySharedPref.getUserId())
                .child(Constants.FAV_REF)
                .child(modelMeal.getIdMeal())
                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@androidx.annotation.NonNull Task<Void> task) {

                    }
                }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        deleteFavMeal(modelMeal);
                    }
                });
    }
}
