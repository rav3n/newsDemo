package com.seldon.news.screens.menu.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.seldon.news.R;
import com.seldon.news.common.app.NewsApplication;
import com.seldon.news.screens.menu.di.DaggerMenuComponent;
import com.seldon.news.screens.menu.di.MenuUIModule;

import javax.inject.Inject;

public class MenuFragment extends Fragment implements MenuView {

    @Inject
    protected WebView webView;

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_menu, container, false);
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inject();
        initWeb();
    }

    private void initWeb() {
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
    }

    private void inject() {
        DaggerMenuComponent.builder()
                .applicationComponent(NewsApplication.getComponent())
                .menuUIModule(new MenuUIModule(getView(), this, new MenuRouter(getActivity())))
                .build()
                .inject(this);
    }

    @Override public void showPage(String url) {
        webView.loadUrl(url);
    }
}
