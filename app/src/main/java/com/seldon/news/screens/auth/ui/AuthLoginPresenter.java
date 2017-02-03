package com.seldon.news.screens.auth.ui;

import android.support.annotation.Nullable;

import com.seldon.news.common.user.domain.UserInteractor;
import com.seldon.news.screens.auth.data.AuthRequestEntity;
import com.seldon.news.screens.auth.data.AuthResponseEntity;
import com.seldon.news.screens.auth.domain.AuthLoginInteractor;

import fw.v6.core.domain.V6BasePresenter;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

public class AuthLoginPresenter extends V6BasePresenter<AuthView, AuthRouter> {

    private CompositeSubscription subscriptions = new CompositeSubscription();

    private AuthLoginInteractor interactor;
    private Observable<AuthRequestEntity> observableRequest;
    private UserInteractor userInteractor;

    private boolean dataValid = true;

    public AuthLoginPresenter(@Nullable AuthView authView,
                              @Nullable AuthRouter router,
                              AuthLoginInteractor interactor,
                              Observable<AuthRequestEntity> observableRequest,
                              UserInteractor userInteractor) {
        super(authView, router);
        this.interactor = interactor;
        this.observableRequest = observableRequest;
        this.userInteractor = userInteractor;
    }

    public void send() {
        // Валидацию пока не делаю
        if (dataValid) {
            tryLogin();
        }
    }

    private void tryLogin() {
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
                        saveUser();
                        saveToken(responseEntity.getToken());
                    }
                })
        );
    }

    private void saveToken(String token) {
        userInteractor.onAuthorized(token);
    }

    private void saveUser() {
        observableRequest.subscribe(new Action1<AuthRequestEntity>() {
            @Override
            public void call(AuthRequestEntity entity) {
                userInteractor.saveCredentials(entity.getName(), entity.getPassword());
            }
        });
    }

    /**
     * rx штуки для отписпи\подписки
     */
    private void registerSubscription(Subscription subscription) {
        subscriptions.add(subscription);
    }

    private void unregisterSubscription() {
        subscriptions.unsubscribe();
    }

    @Override public void onCreate() {}

    @Override public void onDestroy() {
        unregisterSubscription();
    }
}
