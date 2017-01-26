package com.seldon.news.screens.splash.ui;

import android.support.annotation.Nullable;

import com.seldon.news.common.user.data.UserEntity;

import java.util.concurrent.TimeUnit;

import fw.v6.core.domain.V6BasePresenter;
import rx.Observable;
import rx.Scheduler;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SplashSendPresenter extends V6BasePresenter<SplashView, SplashRouter> {

    private Scheduler ui;
    private UserEntity user;

    public SplashSendPresenter(@Nullable SplashView view,
                               @Nullable SplashRouter router,
                               UserEntity user,
                               Scheduler ui) {
        super(view, router);
        this.ui = ui;
        this.user = user;
    }

    @Override
    public void onCreate() {
        Observable.just(this)
                .subscribeOn(Schedulers.io())
                .delay(3, TimeUnit.SECONDS)
                .observeOn(ui)
                .subscribe(new Action1<Object>() {
                    @Override public void call(Object object) {
                        getRouter().startAuth();
                    }
                });
    }

    @Override
    public void onDestroy() {

    }
}
