package com.cnkaptan.nebenanandroidchallange.di;

import com.cnkaptan.nebenanandroidchallange.BuildConfig;
import com.cnkaptan.nebenanandroidchallange.service.GithubService;
import com.cnkaptan.nebenanandroidchallange.service.RetrofitImpl;
import com.cnkaptan.nebenanandroidchallange.ui.main.MainContract;
import com.cnkaptan.nebenanandroidchallange.ui.main.MainPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cihankaptan on 03/08/16.
 */

@Module
public class AppModule {

    @Provides
    public OkHttpClient provideLoggingCapableHttpClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }

    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl(GithubService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }


    @Provides
    @Singleton
    public GithubService provideService(Retrofit retrofit){
        return retrofit.create(GithubService.class);
    }


    @Provides
    @Singleton
    public RetrofitImpl provideService(){
        return new RetrofitImpl();
    }

    @Provides
    public MainContract.MainPresenter provideMainPresenter(RetrofitImpl retrofit){
        return new MainPresenterImpl(retrofit);
    }
}
