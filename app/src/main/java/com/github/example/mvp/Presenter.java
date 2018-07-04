package com.github.example.mvp;

import android.support.annotation.NonNull;
import android.util.Log;

import com.github.example.datamodel.IDataModel;
import com.github.example.model.Repo;
import com.github.example.model.User;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Presenter implements IPresenter {

    private static final String TAG = Presenter.class.getSimpleName();

    @NonNull
    private final IDataModel dataModel;

    @NonNull
    private final IView view;

    private CompositeDisposable disposable;

    public Presenter(@NonNull IDataModel dataModel, @NonNull IView view) {
        this.dataModel = dataModel;
        this.view = view;
    }

    @Override
    public void bind() {
        Log.d(TAG, "bind()");
        disposable = new CompositeDisposable();
    }

    @Override
    public void add(Disposable disposable) {
        this.disposable.add(disposable);
    }

    @Override
    public void unbind() {
        Log.d(TAG, "unbind()");
        disposable.dispose();
    }

    @Override
    public void getUser(String username) {
        dataModel.getGithubUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe()");
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(User user) {
                        Log.d(TAG, "onNext() -> " + user);
                        view.setUser(user);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError() -> ", e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete()");
                    }
                });
    }

    @Override
    public void getUserRepos(String username) {
        dataModel.getGithubUserRepos(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Repo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe()");
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(List<Repo> repos) {
                        Log.d(TAG, "onNext() -> " + repos);
                        view.setRepos(repos);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError() -> ", e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete()");
                    }
                });
    }
}
