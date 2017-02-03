package com.seldon.news.screens.menu.ui;

import com.seldon.news.common.base.data.NewsException;
import com.seldon.news.common.rubrics.data.RubricEntity;
import com.seldon.news.common.rubrics.domain.RubricsInteractor;
import com.seldon.news.screens.menu.data.AllRubricsModel;
import com.seldon.news.screens.menu.domain.MenuRubricsPresenter;

import org.junit.Test;

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MenuRubricsPresenterTest {

    @Test public void loadAllRubricsTest() {
        Scheduler scheduler = Schedulers.immediate();
        MenuView view = mock(MenuView.class);
        MenuRouter router = mock(MenuRouter.class);
        RubricsInteractor interactor = mock(RubricsInteractor.class);

        when(interactor.fetchCustomUserRubrics()).thenReturn(Observable.just(new RubricEntity[0]));
        when(interactor.fetchRubrics()).thenReturn(Observable.just(new RubricEntity[0]));

        MenuRubricsPresenter presenter = new MenuRubricsPresenter(view, router, scheduler, scheduler, interactor);
        presenter.loadAllRubrics();
        verify(view).onRubricsLoaded((AllRubricsModel) any());
    }

    @Test public void loadAllRubricsErrorTest() {
        Scheduler scheduler = Schedulers.immediate();
        MenuView view = mock(MenuView.class);
        MenuRouter router = mock(MenuRouter.class);
        RubricsInteractor interactor = mock(RubricsInteractor.class);
        NewsException exception = new NewsException(0, "");

        when(interactor.fetchCustomUserRubrics()).thenReturn(Observable.<RubricEntity[]>error(exception));
        when(interactor.fetchRubrics()).thenReturn(Observable.just(new RubricEntity[0]));

        MenuRubricsPresenter presenter = new MenuRubricsPresenter(view, router, scheduler, scheduler, interactor);
        presenter.loadAllRubrics();
        verify(view).showError(exception.getMessage());
    }
}
