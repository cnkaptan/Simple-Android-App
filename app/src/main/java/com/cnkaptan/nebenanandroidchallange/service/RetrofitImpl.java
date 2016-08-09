package com.cnkaptan.nebenanandroidchallange.service;

import com.cnkaptan.nebenanandroidchallange.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cihankaptan on 09/08/16.
 */

public class RetrofitImpl {
    private static final String BASE_URL = "";
    private GithubService githubApi;

    public RetrofitImpl() {
        this(BASE_URL);
    }

    private RetrofitImpl(String url){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(provideLoggingCapableHttpClient())
                .build();

        githubApi = retrofit.create(GithubService.class);
    }

    private OkHttpClient provideLoggingCapableHttpClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }

    public GithubService getGithubApi(){
        return githubApi;
    }
}
