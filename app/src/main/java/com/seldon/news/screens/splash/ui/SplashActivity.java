package com.seldon.news.screens.splash.ui;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Activity;

import com.seldon.news.R;
import com.seldon.news.common.app.NewsApplication;
import com.seldon.news.common.user.data.UserEntity;
import com.seldon.news.screens.menu.ui.MenuActivity;
import com.seldon.news.screens.splash.di.DaggerSplashComponent;
import com.seldon.news.screens.splash.di.SplashUIModule;

import javax.inject.Inject;

public class SplashActivity extends Activity implements SplashView {

    @Inject
    protected UserEntity userEntity;
    @Inject
    protected SplashSendPresenter presenter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        inject();
        presenter.onCreate();
    }

    private void inject() {
        DaggerSplashComponent.builder()
                .applicationComponent(NewsApplication.getComponent())
                .splashUIModule(new SplashUIModule(this, new SplashRouter(this)))
                .build()
                .inject(this);
    }

    private void runMenuScreen() {
        MenuActivity.start(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
