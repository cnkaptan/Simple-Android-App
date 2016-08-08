package com.cnkaptan.nebenanandroidchallange.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.cnkaptan.nebenanandroidchallange.ApiActivity;
import com.cnkaptan.nebenanandroidchallange.R;
import com.cnkaptan.nebenanandroidchallange.model.DetailedUser;
import com.cnkaptan.nebenanandroidchallange.model.Repo;
import com.cnkaptan.nebenanandroidchallange.ui.browser.BrowserActivity;
import com.cnkaptan.nebenanandroidchallange.utils.DialogUtils;
import com.cnkaptan.nebenanandroidchallange.utils.ItemClickSupport;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class UserDetailActivity extends ApiActivity implements ItemClickSupport.OnItemClickListener {

    public static final String USER_NAME = "user_name";
    private static final String TAG = UserDetailActivity.class.getSimpleName();
    @Bind(R.id.iv_user_avatar)
    ImageView ivUserAvatar;
    @Bind(R.id.app_bar)
    Toolbar appBar;
    @Bind(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @Bind(R.id.recyclerview)
    RecyclerView rvRepos;
    DetailListAdapter detailListAdapter;
    private String mUserName;

    Subscription subscriptionUserDetail;
    Subscription subscriptionRepos;

    public static Intent newInstance(Context context, String username) {
        Intent intent = new Intent(context, UserDetailActivity.class);
        intent.putExtra(USER_NAME, username);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserName = getIntent().getStringExtra(USER_NAME);
        collapsingToolbarLayout.setTitle(mUserName);

        appBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rvRepos.setLayoutManager(new LinearLayoutManager(this));
        ItemClickSupport.addTo(rvRepos).setOnItemClickListener(this);


        subscriptionUserDetail = githubApi.getObservableUserDetail(mUserName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DetailedUser>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        DialogUtils.showGeneralErrorDialog(getContext(), errorDefine(e));
                    }

                    @Override
                    public void onNext(DetailedUser detailedUser) {
                        Picasso.with(getContext()).load(detailedUser.getAvatar_url()).into(ivUserAvatar);
                    }
                });

        subscriptionUserDetail = githubApi.getObservableRepos(mUserName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Repo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        DialogUtils.showGeneralErrorDialog(getContext(), errorDefine(e));
                    }

                    @Override
                    public void onNext(List<Repo> repos) {
                        detailListAdapter = new DetailListAdapter(repos, getContext());
                        rvRepos.setAdapter(detailListAdapter);
                    }
                });


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_detail;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ItemClickSupport.removeFrom(rvRepos);

        subscriptionUnscribe(subscriptionUserDetail);
        subscriptionUnscribe(subscriptionRepos);

    }

    @Override
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        startActivity(BrowserActivity.newInstance(getContext(),
                getContext().getString(R.string.github_repo_url_format, mUserName, detailListAdapter.getItem(position).getName()),
                BrowserActivity.FULL_URL));
    }



}
