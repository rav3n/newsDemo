package com.seldon.news.screens.auth.ui;

import com.seldon.news.common.app.NewsException;
import com.seldon.news.screens.auth.data.AuthRequestEntity;
import com.seldon.news.screens.auth.data.AuthResponseEntity;
import com.seldon.news.screens.auth.domain.AuthSendInteractor;

import org.junit.Test;

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthSendPresenterTest {

    @Test public void sendTest() {
        /**
         * мокаем сущности
         */
        Scheduler scheduler = Schedulers.immediate();
        AuthView view = mock(AuthView.class);
        AuthRouter router = mock(AuthRouter.class);
        AuthSendInteractor interactor = mock(AuthSendInteractor.class);
        Observable<AuthRequestEntity> observable = Observable.just(mock(AuthRequestEntity.class));

        when(interactor.getResponse((AuthRequestEntity) any())).thenReturn(Observable.just(mock(AuthResponseEntity.class)));

        AuthSendPresenter presenter = new AuthSendPresenter(view, router, interactor,  observable, scheduler);
        presenter.send();

        /**
         * проверяем вызываются ли наши методы
         */
        verify(view).enableProgressDialog(true);
        verify(view).enableProgressDialog(false);
        verify(router).startMenu();
    }

    @Test public void sendErrorTest() {
        Scheduler scheduler = Schedulers.immediate();
        AuthView view = mock(AuthView.class);
        AuthRouter router = mock(AuthRouter.class);
        AuthSendInteractor interactor = mock(AuthSendInteractor.class);
        Observable<AuthRequestEntity> observable = Observable.just(mock(AuthRequestEntity.class));

        when(interactor.getResponse((AuthRequestEntity) any())).thenReturn(Observable.<AuthResponseEntity>error(new NewsException(0, "")));

        AuthSendPresenter presenter = new AuthSendPresenter(view, router, interactor, observable, scheduler);
        presenter.send();

        /**
         * проверяем вызываются ли наши методы
         */
        verify(view).enableProgressDialog(true);
        verify(view).enableProgressDialog(false);
        verify(view).showToast((String) any());
    }
}
