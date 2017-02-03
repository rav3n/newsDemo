package com.seldon.news.screens.menu.di;

import com.seldon.news.common.rubrics.domain.RubricsInteractor;
import com.seldon.news.screens.menu.domain.MenuRubricsPresenter;
import com.seldon.news.screens.menu.ui.MenuRouter;
import com.seldon.news.screens.menu.ui.MenuView;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
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
