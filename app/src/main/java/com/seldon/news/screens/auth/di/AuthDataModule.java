package com.seldon.news.screens.auth.di;

import com.seldon.news.screens.auth.data.AuthApiProvider;
import com.seldon.news.screens.auth.data.AuthResponseEntity;

import dagger.Module;
import dagger.Provides;
import rx.Observable;

@Module
public class AuthDataModule {
    /**
     * Тут провайдим апи интерфейс, потом тут заменю его на ретрофит
     */
    @Provides AuthApiProvider providerAuthApi() {
        return new AuthApiProvider() {
            @Override public Observable<AuthResponseEntity> login(String name, String password, boolean rememberMe) {
                return Observable.just(new AuthResponseEntity(666, "ok, " + name +" are login"));
            }
        };
    }
}
