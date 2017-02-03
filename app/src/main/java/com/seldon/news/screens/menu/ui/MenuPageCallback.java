package com.seldon.news.screens.menu.ui;

import android.app.Activity;
import android.webkit.WebView;

import com.seldon.news.screens.menu.data.AllRubricsModel;

public interface MenuPageCallback {

    WebView getWebView();
    AllRubricsModel getRubricsModel();
    Activity getContext();
}
