package com.cnkaptan.nebenanandroidchallange.service;

import com.cnkaptan.nebenanandroidchallange.model.DetailedUser;
import com.cnkaptan.nebenanandroidchallange.model.User;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by cnkaptan on 28/07/16.
 */
public interface GithubService {
    String ENDPOINT = "https://api.github.com";

    @GET("/users")
    void getUsers(Callback<List<User>> usersCallback);

    @GET("/users/{user_name}")
    void getUserDetail(@Path("user_name")String userName, Callback<DetailedUser> detailedUserCallback);
}
