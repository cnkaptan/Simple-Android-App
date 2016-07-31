package com.cnkaptan.nebenanandroidchallange;

import android.os.Bundle;

import com.cnkaptan.nebenanandroidchallange.service.CustomErrorModel;
import com.cnkaptan.nebenanandroidchallange.service.GithubService;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cihankaptan on 29/07/16.
 */
public abstract class ApiActivity extends BaseActivity {

    public GithubService githubApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setApi();
    }


    private void setApi(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GithubService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        githubApi = retrofit.create(GithubService.class);

    }

    public CustomErrorModel errorDefine(Throwable t){
        String errorType;
        String errorDesc;
        if (t instanceof IOException) {
            errorType = "Timeout";
            errorDesc = String.valueOf(t.getCause());
        }
        else if (t instanceof IllegalStateException) {
            errorType = "ConversionError";
            errorDesc = String.valueOf(t.getCause());
        } else {
            errorType = "Other Error";
            errorDesc = String.valueOf(t.getLocalizedMessage());
        }

        return new CustomErrorModel(errorType,errorDesc);
    }


}
