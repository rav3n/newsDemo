package com.seldon.news.screens.auth.di;

import com.seldon.news.screens.auth.data.AuthApiProvider;
import com.seldon.news.screens.auth.domain.AuthSendInteractor;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AuthDomainModule {

    /**
     * Тут провайдим логику
     */
    @Provides AuthApiProvider providerAuthApi(Retrofit retrofit) {
        return retrofit.create(AuthApiProvider.class);
    }

    @Provides public AuthSendInteractor provideSendInteractor(AuthApiProvider provider) {
        return new AuthSendInteractor(provider);
    }
}
