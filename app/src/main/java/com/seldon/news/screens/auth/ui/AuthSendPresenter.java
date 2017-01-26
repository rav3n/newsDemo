package com.seldon.news.screens.auth.ui;

import android.support.annotation.Nullable;

import com.seldon.news.screens.auth.data.AuthRequestEntity;
import com.seldon.news.screens.auth.data.AuthResponseEntity;
import com.seldon.news.screens.auth.domain.AuthSendInteractor;

import fw.v6.core.domain.V6BasePresenter;
import fw.v6.core.utils.V6DebugLogger;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Func1;

public class AuthSendPresenter extends V6BasePresenter<AuthView, AuthRouter> {

    private Subscription subscription;
    private AuthSendInteractor interactor;
    private Observable<AuthRequestEntity> observableRequest;
    private Scheduler ui;

    private boolean dataValid = true;

    public AuthSendPresenter(@Nullable AuthView authView,
                             @Nullable AuthRouter router,
                             AuthSendInteractor interactor,
                             Observable<AuthRequestEntity> observableRequest,
                             Scheduler ui) {
        super(authView, router);
        this.interactor = interactor;
        this.ui = ui;
        this.observableRequest = observableRequest;
    }

    public void send() {
        // Валидацию пока не делаю
        if (dataValid) {
            handler();
        }
    }

    private void handler() {
        registerSubscription(observableRequest
            .flatMap(new Func1<AuthRequestEntity, Observable<AuthResponseEntity>>() {
                @Override public Observable<AuthResponseEntity> call(AuthRequestEntity requestEntity) {
                    return interactor.getResponse(requestEntity);
                }
            })
            .doOnSubscribe(new Action0() {
                @Override public void call() {
                    getView().enableProgressDialog(true);
                }
            })
            .observeOn(ui)
            .subscribe(new Subscriber<AuthResponseEntity>() {
                @Override public void onCompleted() {}

                @Override public void onError(Throwable e) {
                    e.printStackTrace();
                    getView().enableProgressDialog(false);
                    getView().showToast(e.getMessage());
                }

                @Override public void onNext(AuthResponseEntity responseEntity) {
                    getView().enableProgressDialog(false);
                    getRouter().startMenu();
//                    V6DebugLogger.d("token is " + responseEntity.getToken());
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
