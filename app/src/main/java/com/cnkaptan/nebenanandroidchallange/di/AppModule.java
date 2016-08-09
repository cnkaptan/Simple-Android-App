package com.cnkaptan.nebenanandroidchallange.di;

import com.cnkaptan.nebenanandroidchallange.service.RetrofitImpl;
import com.cnkaptan.nebenanandroidchallange.ui.main.MainContract;
import com.cnkaptan.nebenanandroidchallange.ui.main.MainPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cihankaptan on 03/08/16.
 */

@Module
public class AppModule {
    @Provides
    public MainContract.MainPresenter provideMainPresenter(){
        return new MainPresenterImpl(new RetrofitImpl());
    }
}
