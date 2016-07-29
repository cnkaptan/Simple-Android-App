package com.cnkaptan.nebenanandroidchallange.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.cnkaptan.nebenanandroidchallange.ApiActivity;
import com.cnkaptan.nebenanandroidchallange.R;
import com.cnkaptan.nebenanandroidchallange.model.DetailedUser;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class UserDetailActivity extends ApiActivity {

    public static final String USER_NAME = "user_name";
    private static final String TAG = UserDetailActivity.class.getSimpleName();
    private String mUserName;

    public static Intent newInstance(Context context, String username){
        Intent intent = new Intent(context,UserDetailActivity.class);
        intent.putExtra(USER_NAME,username);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserName = getIntent().getStringExtra(USER_NAME);

        githubApi.getUserDetail(mUserName, new Callback<DetailedUser>() {
            @Override
            public void success(DetailedUser detailedUser, Response response) {
                Log.e(TAG,detailedUser.toString());
            }

            @Override
            public void failure(RetrofitError error) {

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
}
