package com.seldon.news.screens.auth.di;

import com.seldon.news.screens.auth.data.AuthApiProvider;
import com.seldon.news.screens.auth.data.AuthRequestEntity;
import com.seldon.news.screens.auth.domain.AuthSendInteractor;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Observable;
import rx.Scheduler;

import static com.seldon.news.common.app.di.ApplicationDomainModule.APP_DOMAIN_IO;
import static com.seldon.news.common.app.di.ApplicationDomainModule.APP_DOMAIN_UI;

@Module
public class AuthDomainModule {

    /**
     * Тут провайдим логику
     */
    @Provides public AuthSendInteractor provideSendInteractor(AuthApiProvider provider,
                                                              Observable<AuthRequestEntity> observable) {
        return new AuthSendInteractor(provider, observable);
    }
}
