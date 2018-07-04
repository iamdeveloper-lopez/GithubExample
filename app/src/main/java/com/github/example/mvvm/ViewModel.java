package com.github.example.mvvm;

import android.support.annotation.NonNull;

import com.github.example.datamodel.IDataModel;
import com.github.example.model.User;

import io.reactivex.Observable;

public class ViewModel {

    @NonNull
    private final IDataModel dataModel;

    public ViewModel(@NonNull IDataModel dataModel) {
        this.dataModel = dataModel;
    }

    @NonNull
    public Observable<User> getUser(String username) {
        return dataModel.getGithubUser(username);
    }

}
