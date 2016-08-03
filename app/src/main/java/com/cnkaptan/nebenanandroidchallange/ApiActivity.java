package com.cnkaptan.nebenanandroidchallange;

import android.os.Bundle;

import com.cnkaptan.nebenanandroidchallange.service.CustomErrorModel;
import com.cnkaptan.nebenanandroidchallange.service.GithubService;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by cihankaptan on 29/07/16.
 */
public abstract class ApiActivity extends BaseActivity {


    @Inject public GithubService githubApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((GithubApplication)getApplication()).getAppComponent().inject(this);
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
