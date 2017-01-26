package com.seldon.news.common.app;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    private static final String KEY_USER_LOGIN = "user_login";
    private static final String KEY_USER_PASSWORD = "user_password";

    private SharedPreferences prefs;

    public Preferences(Context context, String name) {
        this.prefs = context.getSharedPreferences(name, 0);
    }

    public void setUserLogin(final String login) {
        prefs.edit().putString(KEY_USER_LOGIN, login).commit();
    }

    public String getUserLogin() {
        return prefs.getString(KEY_USER_LOGIN, null);
    }

    public void setUserPassword(final String password) {
        prefs.edit().putString(KEY_USER_PASSWORD, password).commit();
    }

    public String getUserPassword() {
        return prefs.getString(KEY_USER_PASSWORD, null);
    }
}
