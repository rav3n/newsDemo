package com.seldon.news.screens.auth.ui;

import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.seldon.news.R;

import fw.v6.core.domain.V6EventBusSender;
import fw.v6.core.ui.V6SimpleErrorFragment;
import fw.v6.core.ui.base.V6BaseActivity;

public class AuthorizationActivity extends V6BaseActivity {

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_authorization);
        V6EventBusSender.toContent();
    }

    @Override protected Fragment getContentFragment() {
        return new AuthFragment();
    }

    @Override protected Fragment getErrorFragment() {
        return new V6SimpleErrorFragment();
    }

    @Override protected Fragment getEmptyFragment() {
        return null;
    }

    @Override protected int getFragmentContainerId() {
        return R.id.activity_auth_fragment_container;
    }
}
