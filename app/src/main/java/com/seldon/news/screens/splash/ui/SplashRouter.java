package com.seldon.news.screens.splash.ui;

import android.content.Context;

import com.seldon.news.screens.auth.ui.AuthorizationActivity;

public class SplashRouter {

    private Context context;

    public SplashRouter(Context context) {
        this.context = context;
    }

    public void startAuth() {
        AuthorizationActivity.start(context);
    }
}
