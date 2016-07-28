package com.cnkaptan.nebenanandroidchallange;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cnkaptan.nebenanandroidchallange.service.GithubService;

import butterknife.ButterKnife;
import retrofit.RestAdapter;

/**
 * Created by cnkaptan on 28/07/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public GithubService githubApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        setApi();
    }


    public abstract int getLayoutId();

    public abstract Context getContext();

    private void setApi(){
         githubApi = new RestAdapter.Builder()
                .setEndpoint(GithubService.ENDPOINT)
                .build()
                .create(GithubService.class);
    }
}
