package com.example.foodplannerapp.nework;

import com.example.foodplannerapp.models.AreaListModel;

import java.util.List;

public interface NetworkDelegateForArea {
    void onSuccessAreaList(List<AreaListModel> AreaList);
    void onFailureAreaList(String errorMsg);
}
