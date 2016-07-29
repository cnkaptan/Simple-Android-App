package com.cnkaptan.nebenanandroidchallange;

import android.os.Bundle;

import com.cnkaptan.nebenanandroidchallange.service.GithubService;

import retrofit.RestAdapter;

/**
 * Created by cihankaptan on 29/07/16.
 */
public abstract class ApiActivity extends BaseActivity {

    public GithubService githubApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setApi();
    }


    private void setApi(){
        githubApi = new RestAdapter.Builder()
                .setEndpoint(GithubService.ENDPOINT)
                .build()
                .create(GithubService.class);
    }
}
