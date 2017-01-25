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

public abstract class NewsBaseDrawerActivity extends V6BaseActivity {

    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;

    @Override protected int getFragmentContainerId() {
        return R.id.activity_base_fragment_container;
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_news_drawer);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setupToolbar();
        setupMenuDrawer();
        showContent();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_news);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void setupMenuDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_news);
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_base_new_drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.empty, R.string.empty);
        drawerLayout.addDrawerListener(drawerToggle);
    }

    @Override protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showContent() {
        V6EventBusSender.toContent();
    }

    public void showError() {
        V6EventBusSender.toError();
    }
}
