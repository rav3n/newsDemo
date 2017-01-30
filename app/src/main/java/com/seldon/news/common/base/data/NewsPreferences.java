package com.seldon.news.common.base.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class NewsPreferences {

    private static final String KEY_COOKIE = "cookie";
    private static final String KEY_USER_LOGIN = "user_login";
    private static final String KEY_USER_PASSWORD = "user_password";

    private SharedPreferences prefs;

    public NewsPreferences(Context context, String name) {
        this.prefs = context.getSharedPreferences(name, 0);
    }

    public void setCookie(final HashSet<String> source) {
        prefs.edit().putStringSet(KEY_COOKIE, source).commit();
    }

    public Set<String> getCookie() {
        return prefs.getStringSet(KEY_COOKIE, null);
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
