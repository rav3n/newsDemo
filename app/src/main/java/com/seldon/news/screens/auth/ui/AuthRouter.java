package com.seldon.news.screens.auth.ui;

import android.content.Context;

import com.seldon.news.screens.menu.ui.MenuActivity;

public class AuthRouter {

    private Context context;

    public AuthRouter(Context context) {
        this.context = context;
    }

    public void startMenu() {
        MenuActivity.start(context);
    }
}
