package com.cnkaptan.nebenanandroidchallange.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cnkaptan.nebenanandroidchallange.BaseActivity;
import com.cnkaptan.nebenanandroidchallange.R;
import com.cnkaptan.nebenanandroidchallange.model.User;

import java.util.List;

import butterknife.Bind;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends BaseActivity {

    @Bind(R.id.rv_user_list)
    RecyclerView rvUserList;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLinearLayoutManager = new LinearLayoutManager(getContext());
        rvUserList.setLayoutManager(mLinearLayoutManager);


        githubApi.getUsers(new Callback<List<User>>() {
            @Override
            public void success(List<User> users, Response response) {
                rvUserList.setAdapter(new UserListAdapter(users,getContext()));
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public Context getContext() {
        return this;
    }
}
