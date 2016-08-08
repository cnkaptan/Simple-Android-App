package com.cnkaptan.nebenanandroidchallange;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import rx.Subscription;

/**
 * Created by cnkaptan on 28/07/16.
 */
public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

    }


    public abstract int getLayoutId();

    public abstract Context getContext();



    public void subscriptionUnscribe(Subscription subscription){
        if (subscription!= null && subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }
}
