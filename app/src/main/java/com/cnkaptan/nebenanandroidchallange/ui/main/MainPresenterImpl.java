package com.cnkaptan.nebenanandroidchallange.ui.main;

import android.os.Bundle;

import com.cnkaptan.nebenanandroidchallange.service.GithubService;
import com.cnkaptan.nebenanandroidchallange.service.RetrofitImpl;

/**
 * Created by cihankaptan on 09/08/16.
 */

public class MainPresenterImpl implements MainContract.MainPresenter {
    GithubService githubapi;

    public MainPresenterImpl(RetrofitImpl retrofitImpl) {
        this.githubapi = retrofitImpl.getGithubApi();
    }

    @Override
    public void onCreate(Bundle savedInstance) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onLoadMore(int id) {

    }

    @Override
    public void firstDataInitialize() {

    }
}
