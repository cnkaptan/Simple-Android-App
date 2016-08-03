package com.cnkaptan.nebenanandroidchallange.di;

import com.cnkaptan.nebenanandroidchallange.ApiActivity;

import dagger.Component;

/**
 * Created by cihankaptan on 03/08/16.
 */

@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(ApiActivity apiActivity);
}
