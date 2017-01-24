package com.seldon.news.screens.auth.domain;

import com.seldon.news.screens.auth.data.AuthApiProvider;
import com.seldon.news.screens.auth.data.AuthRequestEntity;
import com.seldon.news.screens.auth.data.AuthResponseEntity;

import org.junit.Test;

import rx.Observable;
import rx.Scheduler;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthSendInteractorTest {

    private String name = "name";
    private String password = "pwd";
    private boolean rememberMe = true;

    @Test public void getResponseTest() {
        /**
         * мокаем
         */
        Scheduler scheduler = Schedulers.immediate();
        AuthApiProvider provider = mock(AuthApiProvider.class);
        AuthResponseEntity response = mock(AuthResponseEntity.class);
        AuthRequestEntity request = mock(AuthRequestEntity.class);

        when(request.getName()).thenReturn(name);
        when(request.getPassword()).thenReturn(password);
        when(request.isRememberMe()).thenReturn(rememberMe);

        Observable<AuthRequestEntity> observable = Observable.just(request);
        when(provider.login(name, password, rememberMe)).thenReturn(Observable.just(response));

        TestSubscriber testSubscriber = new TestSubscriber();
        int delay = 0;
        AuthSendInteractor interactor = new AuthSendInteractor(provider, observable, delay, scheduler, scheduler);

        interactor.getResponse().subscribe(testSubscriber);
        verify(provider).login(name, password, rememberMe);
        testSubscriber.assertNoErrors();
        testSubscriber.assertValue(response);
    }
}
