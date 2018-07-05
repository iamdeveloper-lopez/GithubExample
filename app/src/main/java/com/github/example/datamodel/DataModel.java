package com.github.example.datamodel;

import com.github.example.api.GithubService;
import com.github.example.api.ServiceGenerator;
import com.github.example.model.Repo;
import com.github.example.model.User;

import java.util.List;

import io.reactivex.Observable;

public class DataModel implements IDataModel {

    @Override
    public Observable<User> getGithubUser(String username) {
        GithubService service = ServiceGenerator.createService(GithubService.class, GithubService.BASE_URL);
        return service.getGithubUser(username);
    }

    @Override
    public Observable<List<Repo>> getGithubUserRepos(String username) {
        GithubService service = ServiceGenerator.createService(GithubService.class, GithubService.BASE_URL);
        return service.getGithubUserRepos(username);
    }

}
