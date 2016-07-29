package com.cnkaptan.nebenanandroidchallange.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.cnkaptan.nebenanandroidchallange.BaseActivity;
import com.cnkaptan.nebenanandroidchallange.R;

import butterknife.Bind;

public class BrowserActivity extends BaseActivity {

    public static final String USER_NAME = "user_name";
    public String mUsername;
    @Bind(R.id.webview)
    WebView webview;

    public static Intent newInstance(Context context, String userName) {
        Intent intent = new Intent(context, BrowserActivity.class);
        intent.putExtra(USER_NAME, userName);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUsername = getIntent().getStringExtra(USER_NAME);


        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.loadUrl(getProfileUrl(mUsername));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_browser;
    }

    @Override
    public Context getContext() {
        return this;
    }

    public String getProfileUrl(String mUsername) {
        return String.format(getString(R.string.github_profile_url),mUsername);
    }
}
