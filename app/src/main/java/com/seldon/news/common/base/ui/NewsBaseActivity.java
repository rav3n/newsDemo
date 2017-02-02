package com.seldon.news.common.base.ui;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.seldon.news.R;

import fw.v6.core.domain.V6EventBusSender;
import fw.v6.core.ui.base.V6BaseActivity;

public abstract class NewsBaseActivity extends V6BaseActivity {

    @Override protected int getFragmentContainerId() {
        return R.id.activity_base_fragment_container;
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_news);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        setupToolbar();
        showContent();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_news);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    public void showContent() {
        V6EventBusSender.toContent();
    }

    public void showError() {
        V6EventBusSender.toError();
    }
}
