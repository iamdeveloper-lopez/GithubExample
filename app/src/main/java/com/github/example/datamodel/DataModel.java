package com.github.example.datamodel;

import com.github.example.api.GithubService;
import com.github.example.model.Repo;
import com.github.example.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataModel implements IDataModel {

    @Override
    public Observable<User> getGithubUser(String username) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com/")
                .build();
        GithubService service = retrofit.create(GithubService.class);
        return service.getGithubUser(username);
    }

    @Override
    public Observable<List<Repo>> getGithubUserRepos(String username) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com/")
                .build();
        GithubService service = retrofit.create(GithubService.class);
        return service.getGithubUserRepos(username);
    }

}
