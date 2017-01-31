package com.seldon.news.screens.auth.ui;

import android.support.annotation.Nullable;

import fw.v6.core.domain.V6BasePresenter;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

public class AuthValidationPresenter extends V6BasePresenter<AuthView, AuthRouter> {

    private Subscription subscription;
    private Observable<Boolean> observable;

    public AuthValidationPresenter(@Nullable AuthView authView,
                                   @Nullable AuthRouter router,
                                   Observable<Boolean> observable) {
        super(authView, router);
        this.observable = observable;
    }

    @Override public void onCreate() {
        subscription = observable.subscribe(new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(Boolean valid) {
                getView().enableButton(valid);
            }
        });
    }

    @Override public void onDestroy() {
        subscription.unsubscribe();
    }
}
