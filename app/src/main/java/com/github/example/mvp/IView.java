package com.github.example.mvp;

import com.github.example.model.Repo;
import com.github.example.model.User;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface IView {

    void setUser(@NonNull final User user);

    void setRepos(@NonNull final List<Repo> repos);

    void onFailed(Throwable throwable);

}
