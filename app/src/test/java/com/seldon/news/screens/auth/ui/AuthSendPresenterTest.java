package com.seldon.news.screens.auth.ui;

import com.seldon.news.screens.auth.data.AuthResponseEntity;
import com.seldon.news.screens.auth.domain.AuthSendInteractor;
import com.seldon.news.screens.auth.ui.AuthRouter;
import com.seldon.news.screens.auth.ui.AuthSendPresenter;
import com.seldon.news.screens.auth.ui.AuthView;

import org.junit.Test;

import rx.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthSendPresenterTest {

    @Test public void sendTest() {
        /**
         * мокаем сущности
         */
        AuthView view = mock(AuthView.class);
        AuthRouter router = mock(AuthRouter.class);
        AuthSendInteractor interactor = mock(AuthSendInteractor.class);

        when(interactor.getResponse()).thenReturn(Observable.just(mock(AuthResponseEntity.class)));

        AuthSendPresenter presenter = new AuthSendPresenter(view, router, interactor);
        presenter.send();

        /**
         * проверяем вызываются ли наши методы
         */
        verify(view).enableProgressDialog(true);
        verify(view).enableProgressDialog(false);
        verify(view).showToast((String) any());
    }
}
