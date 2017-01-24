package com.seldon.news.screens.auth.domain;

import com.seldon.news.screens.auth.data.AuthApiProvider;
import com.seldon.news.screens.auth.data.AuthRequestEntity;
import com.seldon.news.screens.auth.data.AuthResponseEntity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;

public class AuthSendInteractor {

    private AuthApiProvider provider;
    private Scheduler ui;
    private Scheduler io;
    private Observable<AuthRequestEntity> observableRequest;

    private int delay;

    public AuthSendInteractor(AuthApiProvider provider,
                              Observable<AuthRequestEntity> observableRequest,
                              int delay, // временно!!!
                              Scheduler io,
                              Scheduler ui) {
        this.provider = provider;
        this.ui = ui;
        this.io = io;
        this.observableRequest = observableRequest;
        this.delay = delay;
    }

    public Observable<AuthResponseEntity> getResponse() {
        return observableRequest
            .subscribeOn(io)
            .flatMap(new Func1<AuthRequestEntity, Observable<AuthResponseEntity>>() {
                @Override
                public Observable<AuthResponseEntity> call(AuthRequestEntity request) {
                    return provider.login(request.getName(), request.getPassword(), request.isRememberMe());
                }
            })
            .delay(delay, TimeUnit.SECONDS)
            .observeOn(ui);
    }
}
