package com.seldon.news.screens.splash.di;

import com.seldon.news.common.user.data.UserEntity;
import com.seldon.news.common.user.domain.UserInteractor;
import com.seldon.news.screens.auth.domain.AuthLoginInteractor;
import com.seldon.news.screens.splash.ui.SplashRouter;
import com.seldon.news.screens.splash.ui.SplashSendPresenter;
import com.seldon.news.screens.splash.ui.SplashView;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

import static com.seldon.news.common.app.di.ApplicationDomainModule.APP_DOMAIN_IO;

@Module
public class SplashUIModule {

    private SplashView view;
    private SplashRouter router;

    public SplashUIModule(SplashView view, SplashRouter router) {
        this.view = view;
        this.router = router;
    }

    @Provides public SplashSendPresenter provideSendPresenter(
            SplashView view,
            SplashRouter router,
            UserEntity user,
            AuthLoginInteractor interactor,
            UserInteractor userInteractor,
            @Named(APP_DOMAIN_IO) Scheduler io) {
        return new SplashSendPresenter(view, router, user, interactor, userInteractor, io);
    }

    @Provides public SplashView provideView() {
        return view;
    }

    @Provides public SplashRouter provideRouter() {
        return router;
    }
}
