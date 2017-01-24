package com.seldon.news.screens.auth.ui;

import android.support.annotation.Nullable;
import android.support.v4.util.DebugUtils;

import com.seldon.news.screens.auth.data.AuthResponseEntity;
import com.seldon.news.screens.auth.domain.AuthSendInteractor;
import com.seldon.news.screens.auth.ui.AuthRouter;
import com.seldon.news.screens.auth.ui.AuthView;

import fw.v6.core.domain.V6BasePresenter;
import fw.v6.core.utils.V6DebugLogger;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;

public class AuthSendPresenter extends V6BasePresenter<AuthView, AuthRouter> {

    private Subscription subscription;
    private AuthSendInteractor interactor;

    private boolean dataValid = true;

    public AuthSendPresenter(@Nullable AuthView authView,
                             @Nullable AuthRouter router,
                             AuthSendInteractor interactor) {
        super(authView, router);
        this.interactor = interactor;
    }

    public void send() {
        // Валидацию пока не делаю
        if (dataValid) {
            handler();
        }
    }

    private void handler() {
        registerSubscription(interactor.getResponse()
            .doOnSubscribe(new Action0() {
                @Override public void call() {
                    getView().enableProgressDialog(true);
                }
            })
            .subscribe(new Subscriber<AuthResponseEntity>() {
                @Override public void onCompleted() {}

                @Override public void onError(Throwable e) {
                    e.printStackTrace();
                    getView().enableProgressDialog(false);
                    getView().showToast(e.getMessage());
                }

                @Override public void onNext(AuthResponseEntity responseEntity) {
                    getView().enableProgressDialog(false);
                    getView().showToast(responseEntity.getCode() + " " + responseEntity.getMessage());
                }
            })
        );
    }

    /**
     * rx штуки для отписпи\подписки
     */
    private void registerSubscription(Subscription subscription) {
        unregisterSubscription();
        this.subscription = subscription;
    }

    private void unregisterSubscription() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    @Override public void onCreate() {}

    @Override public void onDestroy() {
        unregisterSubscription();
    }
}
