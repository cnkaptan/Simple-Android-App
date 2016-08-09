package com.cnkaptan.nebenanandroidchallange.ui.main;

import android.os.Bundle;

import com.cnkaptan.nebenanandroidchallange.model.User;
import com.cnkaptan.nebenanandroidchallange.service.GithubService;
import com.cnkaptan.nebenanandroidchallange.service.RetrofitImpl;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cihankaptan on 09/08/16.
 */

public class MainPresenterImpl implements MainContract.MainPresenter {
    GithubService githubApi;
    MainContract.MainView mainView;
    private Subscription subscriptionUsers;
    private Subscription subscriptionUsersPagination;

    public MainPresenterImpl(RetrofitImpl retrofitImpl) {
        this.githubApi = retrofitImpl.getGithubApi();
    }

    @Override
    public void onCreate(Bundle savedInstance) {

    }

    @Override
    public void onDestroy() {
        if (subscriptionUsers != null && subscriptionUsers.isUnsubscribed())
            subscriptionUsers.unsubscribe();

        if (subscriptionUsersPagination != null && subscriptionUsersPagination.isUnsubscribed())
            subscriptionUsersPagination.unsubscribe();
    }

    @Override
    public void onLoadMore(int userId) {
        subscriptionUsersPagination = githubApi.getObservableUsersPaginationById(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<User>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.loadServiceFail(e);
                    }

                    @Override
                    public void onNext(List<User> users) {
                        mainView.loadMoreSuccess(users);
                    }
                });
    }

    @Override
    public void firstDataInitialize() {
        subscriptionUsers = githubApi.getObservableUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<User>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.loadServiceFail(e);
                    }

                    @Override
                    public void onNext(List<User> users) {
                        mainView.firstDataInitializeSuccess(users);
                    }
                });
    }

    @Override
    public void setView(MainContract.MainView mainView) {
        this.mainView = mainView;
    }
}
