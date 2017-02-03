package com.seldon.news.screens.auth.ui;

import com.seldon.news.common.base.data.NewsException;
import com.seldon.news.common.user.domain.UserInteractor;
import com.seldon.news.screens.auth.data.AuthRequestEntity;
import com.seldon.news.screens.auth.data.AuthResponseEntity;
import com.seldon.news.screens.auth.domain.AuthLoginInteractor;

import org.junit.Test;

import rx.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthLoginPresenterTest {

    @Test public void sendTest() {
        /**
         * мокаем сущности
         */
        AuthView view = mock(AuthView.class);
        AuthRouter router = mock(AuthRouter.class);
        AuthLoginInteractor interactor = mock(AuthLoginInteractor.class);
        Observable<AuthRequestEntity> observable = Observable.just(mock(AuthRequestEntity.class));
        UserInteractor userInteractor = mock(UserInteractor.class);

        when(interactor.getResponse((AuthRequestEntity) any())).thenReturn(Observable.just(mock(AuthResponseEntity.class)));

        AuthLoginPresenter presenter = new AuthLoginPresenter(view, router, interactor,  observable, userInteractor);
        presenter.send();

        /**
         * проверяем вызываются ли наши методы
         */
        verify(view).enableProgressDialog(true);
        verify(view).enableProgressDialog(false);
        verify(router).startMenu();
        verify(userInteractor).saveCredentials((String) any(), (String) any());
    }

    @Test public void sendErrorTest() {
        AuthView view = mock(AuthView.class);
        AuthRouter router = mock(AuthRouter.class);
        AuthLoginInteractor interactor = mock(AuthLoginInteractor.class);
        Observable<AuthRequestEntity> observable = Observable.just(mock(AuthRequestEntity.class));
        UserInteractor userInteractor = mock(UserInteractor.class);

        when(interactor.getResponse((AuthRequestEntity) any())).thenReturn(Observable.<AuthResponseEntity>error(new NewsException(0, "")));

        AuthLoginPresenter presenter = new AuthLoginPresenter(view, router, interactor, observable, userInteractor);
        presenter.send();

        /**
         * проверяем вызываются ли наши методы
         */
        verify(view).enableProgressDialog(true);
        verify(view).enableProgressDialog(false);
        verify(view).showToast((String) any());
    }
}
