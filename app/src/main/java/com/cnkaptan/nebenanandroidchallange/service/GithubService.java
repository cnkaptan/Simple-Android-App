package com.cnkaptan.nebenanandroidchallange.service;

import com.cnkaptan.nebenanandroidchallange.model.DetailedUser;
import com.cnkaptan.nebenanandroidchallange.model.Repo;
import com.cnkaptan.nebenanandroidchallange.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by cnkaptan on 28/07/16.
 */
public interface GithubService {
    String BASE_URL = "https://api.github.com/";

    @GET("/users")
    Call<List<User>> getUsers();

    @GET("/users")
    Observable<List<User>> getObservableUser();

    @GET("/users/{user_name}")
    Call<DetailedUser> getUserDetail(@Path("user_name")String userName);

    @GET("/users/{user_name}")
    Observable<DetailedUser> getObservableUserDetail(@Path("user_name")String userName);

    @GET("/users/{user_name}/repos")
    Call<List<Repo>> getRepos(@Path("user_name")String userName);

    @GET("/users/{user_name}/repos")
    Observable<List<Repo>> getObservableRepos(@Path("user_name")String userName);

    @GET("/users")
    Call<List<User>> getUsersPaginationById(@Query("since")int sinceUserId);

    @GET("/users")
    Observable<List<User>> getObservableUsersPaginationById(@Query("since")int sinceUserId);
}
