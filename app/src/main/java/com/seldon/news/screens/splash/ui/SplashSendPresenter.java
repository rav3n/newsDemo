package com.seldon.news.screens.splash.ui;

import android.support.annotation.Nullable;

import com.seldon.news.common.user.data.UserEntity;
import com.seldon.news.screens.auth.data.AuthRequestEntity;
import com.seldon.news.screens.auth.data.AuthResponseEntity;
import com.seldon.news.screens.auth.domain.AuthSendInteractor;

import java.util.concurrent.TimeUnit;

import fw.v6.core.domain.V6BasePresenter;
import fw.v6.core.utils.V6DebugLogger;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

public class SplashSendPresenter extends V6BasePresenter<SplashView, SplashRouter> {

    private static final int SPLASH_DELAY = 3;

    private Scheduler io;
    private UserEntity user;
    private AuthSendInteractor interactor;

    private Subscription subscription;

    public SplashSendPresenter(@Nullable SplashView view,
                               @Nullable SplashRouter router,
                               UserEntity user,
                               AuthSendInteractor interactor,
                               Scheduler io) {
        super(view, router);
        this.io = io;
        this.user = user;
        this.interactor = interactor;
    }

    private boolean hasLoginAndPwd() {
        String login = user.getLogin();
        String password = user.getPassword();
        V6DebugLogger.d("login = " + login + ", " + password);
        return login != null && password != null;
    }

    private void tryAuthentication() {
        registerSubscription(Observable.just(user)
            .observeOn(io)
            .map(new Func1<UserEntity, AuthRequestEntity>() {
                @Override
                public AuthRequestEntity call(UserEntity userEntity) {
                    return new AuthRequestEntity(userEntity.getLogin(), userEntity.getPassword(), true);
                }
            })
            .flatMap(new Func1<AuthRequestEntity, Observable<AuthResponseEntity>>() {
                @Override public Observable<AuthResponseEntity> call(AuthRequestEntity requestEntity) {
                    return interactor.getResponse(requestEntity);
                }
            })
            .subscribe(new Subscriber<AuthResponseEntity>() {
                @Override public void onCompleted() {}

                @Override public void onError(Throwable e) {
                    e.printStackTrace();
                    getRouter().startAuth();
                }

                @Override public void onNext(AuthResponseEntity responseEntity) {
                    getRouter().startMain();
                }
            })
        );
    }

    private void justShowSplash() {
        registerSubscription(Observable.just(this)
            .observeOn(io)
            .delay(SPLASH_DELAY, TimeUnit.SECONDS)
            .subscribe(new Action1<Object>() {
                @Override public void call(Object object) {
                    getRouter().startAuth();
                }
            }
        ));
    }

    @Override public void onCreate() {
        if (hasLoginAndPwd()) {
            tryAuthentication();
        } else {
            justShowSplash();
        }
    }

    @Override public void onDestroy() {
        unregisterSubscription();
    }

    private void registerSubscription(Subscription subscription) {
        unregisterSubscription();
        this.subscription = subscription;
    }

    private void unregisterSubscription() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }
}
