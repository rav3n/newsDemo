package com.seldon.news.screens.splash;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Activity;

import com.seldon.news.R;
import com.seldon.news.screens.menu.ui.MenuActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SplashActivity extends Activity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Observable.just(this)
            .subscribeOn(Schedulers.io())
            .delay(3, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<SplashActivity>() {
                @Override public void call(SplashActivity splashActivity) {
                    runMenuScreen();
            }
        });
    }

    private void runMenuScreen() {
        MenuActivity.start(this);
    }
}
