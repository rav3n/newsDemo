package com.seldon.news.common.rubrics.di;

import com.seldon.news.common.rubrics.data.ApiRubricsProvider;
import com.seldon.news.common.rubrics.domain.RubricsInteractor;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class RubricsModule {

    @Provides public ApiRubricsProvider providerApi(Retrofit retrofit) {
        return retrofit.create(ApiRubricsProvider.class);
    }

    @Provides public RubricsInteractor provideRubricsInteractor(ApiRubricsProvider provider) {
        return new RubricsInteractor(provider);
    }
}
