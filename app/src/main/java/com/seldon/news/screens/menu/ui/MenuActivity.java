package com.seldon.news.screens.menu.ui;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.seldon.news.common.base.ui.NewsBaseDrawerActivity;

import fw.v6.core.ui.V6SimpleErrorFragment;

public class MenuActivity extends NewsBaseDrawerActivity {

    public static void start(Context runner) {
        Intent intent = new Intent(runner, MenuActivity.class);
        runner.startActivity(intent);
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override protected Fragment getContentFragment() {
        return new MenuFragment();
    }

    @Override protected Fragment getErrorFragment() {
        return new V6SimpleErrorFragment();
    }

    @Override protected Fragment getEmptyFragment() {
        return null;
    }
}
