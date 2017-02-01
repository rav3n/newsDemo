package com.seldon.news.screens.menu.di;

import com.seldon.news.common.app.di.ApplicationDomainModule;
import com.seldon.news.common.base.data.NewsResponse;
import com.seldon.news.common.rubrics.data.ApiRubricsProvider;
import com.seldon.news.common.rubrics.data.RubricEntity;
import com.seldon.news.common.rubrics.domain.RubricsInteractor;
import com.seldon.news.screens.menu.domain.MenuRubricsPresenter;
import com.seldon.news.screens.menu.ui.MenuRouter;
import com.seldon.news.screens.menu.ui.MenuView;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Observable;
import rx.Scheduler;

import static com.seldon.news.common.app.di.ApplicationDomainModule.APP_DOMAIN_IO;
import static com.seldon.news.common.app.di.ApplicationDomainModule.APP_DOMAIN_UI;

@Module
public class MenuDomainModule {

    @Provides public MenuRubricsPresenter presenterRubricsPresenter(MenuView view,
                                                                    MenuRouter router,
                                                                    @Named(APP_DOMAIN_IO)
                                                                    Scheduler io,
                                                                    @Named(APP_DOMAIN_UI)
                                                                    Scheduler ui,
                                                                    RubricsInteractor rubricsInteractor) {
        return new MenuRubricsPresenter(view, router, io, ui, rubricsInteractor);
    }

}
