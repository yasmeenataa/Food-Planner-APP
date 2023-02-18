package com.example.foodplannerapp.ui.detailsFragment.presenter;

import androidx.lifecycle.MutableLiveData;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public interface DetailsPresenterInterface {

    MutableLiveData<Integer> getProgressBarLiveData();

    CompositeDisposable getDisposable();
}
