package com.cnkaptan.nebenanandroidchallange;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cnkaptan.nebenanandroidchallange.service.CustomErrorModel;

import java.io.IOException;

import butterknife.ButterKnife;

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
