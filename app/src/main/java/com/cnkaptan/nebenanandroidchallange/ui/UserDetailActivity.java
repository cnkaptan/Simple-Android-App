package com.cnkaptan.nebenanandroidchallange.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.cnkaptan.nebenanandroidchallange.ApiActivity;
import com.cnkaptan.nebenanandroidchallange.R;
import com.cnkaptan.nebenanandroidchallange.model.DetailedUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

        Call<DetailedUser> detailedUserCall = githubApi.getUserDetail(mUserName);
        detailedUserCall.enqueue(new Callback<DetailedUser>() {
            @Override
            public void onResponse(Call<DetailedUser> call, Response<DetailedUser> response) {
                Log.e(TAG,response.body().toString());
            }

            @Override
            public void onFailure(Call<DetailedUser> call, Throwable t) {

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
