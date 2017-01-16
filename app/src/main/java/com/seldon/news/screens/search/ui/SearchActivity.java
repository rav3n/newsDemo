package com.seldon.news.screens.search.ui;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.seldon.news.R;

import fw.v6.core.domain.V6EventBusSender;
import fw.v6.core.ui.V6SimpleErrorFragment;
import fw.v6.core.ui.base.V6BaseActivity;

public class SearchActivity extends V6BaseActivity {

    private static String SEARCH_ARG = "search_arg";

    public static void startForResult(Activity runner, String searchTarget, int requestCode) {
        Intent intent = new Intent(runner, SearchActivity.class);
        intent.putExtra(SEARCH_ARG, searchTarget);
        runner.startActivityForResult(intent, requestCode);
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        showContent();
    }

    private void showContent() {
        String searchTarget = getSearchTarget();
        Bundle bundle = null;
        if (searchTarget != null) {
            bundle = SearchFragment.createFragmentBundle(searchTarget);
        }
        V6EventBusSender.toContent(bundle);
    }

    @Override protected Fragment getContentFragment() {
        return new SearchFragment();
    }

    @Override protected Fragment getErrorFragment() {
        return new V6SimpleErrorFragment();
    }

    @Nullable private String getSearchTarget() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(SEARCH_ARG)) {
            return extras.getString(SEARCH_ARG);
        }
        return null;
    }

    /**
     * невозможно в данном контексте, если активити пуста, это ошибка!
     */
    @Override protected Fragment getEmptyFragment() {
        return null;
    }
}
