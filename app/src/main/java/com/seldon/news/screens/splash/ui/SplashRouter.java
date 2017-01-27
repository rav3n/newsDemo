package com.seldon.news.screens.splash.ui;

import android.app.Activity;

import com.seldon.news.screens.auth.ui.AuthorizationActivity;
import com.seldon.news.screens.menu.ui.MenuActivity;

public class SplashRouter {

    private Activity context;

    public SplashRouter(Activity context) {
        this.context = context;
    }

    public void startAuth() {
        context.finish();
        AuthorizationActivity.start(context);
    }

    public void startMain() {
        context.finish();
        MenuActivity.start(context);
    }
}
