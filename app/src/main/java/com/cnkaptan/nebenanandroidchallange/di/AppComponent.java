package com.cnkaptan.nebenanandroidchallange.di;

import com.cnkaptan.nebenanandroidchallange.ui.detail.UserDetailActivity;
import com.cnkaptan.nebenanandroidchallange.ui.main.MainActivity;

import dagger.Component;

/**
 * Created by cihankaptan on 03/08/16.
 */

@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
    void inject(UserDetailActivity userDetailActivity);
}
