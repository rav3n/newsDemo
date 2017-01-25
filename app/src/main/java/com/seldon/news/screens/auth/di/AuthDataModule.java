package com.seldon.news.screens.auth.di;

import com.seldon.news.screens.auth.data.AuthApiProvider;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AuthDataModule {
    @Provides AuthApiProvider providerAuthApi(Retrofit retrofit) {
        return retrofit.create(AuthApiProvider.class);
    }
}
