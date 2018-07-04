package com.github.example.mvp;

import io.reactivex.disposables.Disposable;

public interface IPresenter {

    void bind();

    void add(Disposable disposable);

    void unbind();

    void getUser(String username);

    void getUserRepos(String username);

}
