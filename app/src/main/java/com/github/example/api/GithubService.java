package com.github.example.api;

import com.github.example.model.Repo;
import com.github.example.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubService {

    @GET("users/{username}")
    Observable<User> getGithubUser(@Path("username") String username);

    @GET("users/{username}/repos")
    Observable<List<Repo>> getGithubUserRepos(@Path("username") String username);

}
