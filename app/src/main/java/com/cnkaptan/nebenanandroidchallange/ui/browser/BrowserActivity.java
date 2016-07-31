package com.cnkaptan.nebenanandroidchallange.ui.browser;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.cnkaptan.nebenanandroidchallange.BaseActivity;
import com.cnkaptan.nebenanandroidchallange.R;

import butterknife.Bind;

public class BrowserActivity extends BaseActivity {

    public static final String URL = "url";
    public static final String FLAG = "flag";
    public static final int USERNAME = 100;
    public static final int FULL_URL = 101;
    private static final int DEFAULT_VALUE = 0;
    public String mURl;
    public int flag;
    @Bind(R.id.webview)
    WebView webview;

    public static Intent newInstance(Context context, String url,int flag) {
        Intent intent = new Intent(context, BrowserActivity.class);
        intent.putExtra(URL, url);
        intent.putExtra(FLAG,flag);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mURl = getIntent().getStringExtra(URL);
        flag = getIntent().getIntExtra(FLAG, DEFAULT_VALUE);

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        if(flag == USERNAME){
            webview.loadUrl(getProfileUrl(mURl));
        }else if (flag == FULL_URL){
            webview.loadUrl(mURl);
        }else{
            finish();
        }



    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_browser;
    }

    @Override
    public Context getContext() {
        return this;
    }

    private String getProfileUrl(String mUsername) {
        return String.format(getString(R.string.github_profile_url),mUsername);
    }
}
