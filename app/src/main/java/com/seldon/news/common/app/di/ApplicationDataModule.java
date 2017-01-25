package com.seldon.news.common.app.di;

import com.seldon.news.common.app.RetrofitBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

import static com.seldon.news.common.Const.SERVER_URL;

@Module
public class ApplicationDataModule {
    @Singleton @Provides public Retrofit provideRetrofit() {
        return RetrofitBuilder.build(SERVER_URL);
    }
}
