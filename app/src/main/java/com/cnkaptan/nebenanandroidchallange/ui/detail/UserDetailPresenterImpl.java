package com.cnkaptan.nebenanandroidchallange.ui.detail;

import com.cnkaptan.nebenanandroidchallange.model.DetailedUser;
import com.cnkaptan.nebenanandroidchallange.model.Repo;
import com.cnkaptan.nebenanandroidchallange.service.GithubService;
import com.cnkaptan.nebenanandroidchallange.service.RetrofitImpl;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cnkaptan on 09/08/16.
 */
public class UserDetailPresenterImpl implements UserDetailContract.UserDetailPresenter {
    GithubService githubApi;
    UserDetailContract.UserDetailView userDetailView;
    private Subscription subscriptionUserDetail;
    private Subscription subscriptionRepos;

    public UserDetailPresenterImpl(RetrofitImpl githubApi) {
        this.githubApi = githubApi.getGithubApi();
    }

    @Override
    public void setView(UserDetailContract.UserDetailView userDetailView) {
      this.userDetailView = userDetailView;
    }

    @Override
    public void initDatas(String mUserName) {

        subscriptionUserDetail = githubApi.getObservableUserDetail(mUserName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DetailedUser>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        userDetailView.onRequestFail(e);
                    }

                    @Override
                    public void onNext(DetailedUser detailedUser) {
                        userDetailView.getDetailedUserSuccess(detailedUser);
                    }
                });

        subscriptionRepos = githubApi.getObservableRepos(mUserName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Repo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        userDetailView.onRequestFail(e);
                    }

                    @Override
                    public void onNext(List<Repo> repos) {
                       userDetailView.getReposSuccess(repos);
                    }
                });
    }


    @Override
    public void onDestroy() {
        if (subscriptionUserDetail != null && subscriptionUserDetail.isUnsubscribed())
            subscriptionUserDetail.unsubscribe();

        if (subscriptionRepos != null && subscriptionRepos.isUnsubscribed())
            subscriptionRepos.unsubscribe();
    }
}
