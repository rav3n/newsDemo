package com.seldon.news.common.user.domain;

import com.seldon.news.common.base.data.NewsException;
import com.seldon.news.common.base.data.NewsPreferences;
import com.seldon.news.common.base.data.NewsResponse;
import com.seldon.news.common.user.data.UserEntity;
import com.seldon.news.screens.auth.data.AuthApiProvider;
import com.seldon.news.screens.auth.data.AuthRequestEntity;
import com.seldon.news.screens.auth.data.AuthResponseEntity;
import com.seldon.news.screens.auth.domain.AuthSendInteractor;

import org.junit.Test;

import rx.Observable;
import rx.observers.TestSubscriber;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserInteractorTest {

    private int code = 666;
    private String error = "error";
    private String name = "name";
    private String password = "pwd";

    @Test public void test() {

//        NewsPreferences preferences = mock(NewsPreferences.class);
//        UserEntity user = mock(UserEntity.class);
//        UserInteractor interactor = new UserInteractor(preferences, user);
//
//        AuthApiProvider provider = mock(AuthApiProvider.class);
//        NewsResponse<AuthResponseEntity> response = mock(NewsResponse.class);
//        AuthResponseEntity responseEntity = mock(AuthResponseEntity.class);
//        AuthRequestEntity request = mock(AuthRequestEntity.class);
//
//        when(request.getName()).thenReturn(name);
//        when(request.getPassword()).thenReturn(password);
//        when(request.isRememberMe()).thenReturn(rememberMe);
//
//        when(provider.login(name, password, rememberMe)).thenReturn(Observable.just(response));
//        when(response.isSuccessful()).thenReturn(true);
//        when(response.getResponse()).thenReturn(responseEntity);
//
//
//        TestSubscriber testSubscriber = new TestSubscriber();
//        AuthSendInteractor interactor = new AuthSendInteractor(provider);
//
//        interactor.getResponse(request).subscribe(testSubscriber);
//        verify(provider).login(name, password, rememberMe);
//        testSubscriber.assertNoErrors();
//        testSubscriber.assertValue(responseEntity);
    }
}
