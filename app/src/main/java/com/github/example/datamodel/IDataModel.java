package com.github.example.datamodel;

import com.github.example.model.Repo;
import com.github.example.model.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

public interface IDataModel {

    @NonNull
    Observable<User> getGithubUser(String username);

    @NonNull
    Observable<List<Repo>> getGithubUserRepos(String username);

}
