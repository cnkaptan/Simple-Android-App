package com.cnkaptan.nebenanandroidchallange;

import android.app.Application;

import com.cnkaptan.nebenanandroidchallange.di.AppComponent;
import com.cnkaptan.nebenanandroidchallange.di.AppModule;
import com.cnkaptan.nebenanandroidchallange.di.DaggerAppComponent;

/**
 * Created by cihankaptan on 03/08/16.
 */

public class GithubApplication extends Application{

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();


        createAppComponent();
    }


    public void createAppComponent(){
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
    }


    public AppComponent getAppComponent(){
        return appComponent;
    }
}
