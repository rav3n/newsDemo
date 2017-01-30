package com.seldon.news.common.app.di;

import android.content.Context;

import com.seldon.news.common.app.NewsApplication;
import com.seldon.news.common.base.data.Preferences;
import com.seldon.news.common.user.data.UserEntity;
import com.seldon.news.common.user.domain.UserInteractor;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;
import rx.Scheduler;

import static com.seldon.news.common.app.di.ApplicationDomainModule.APP_DOMAIN_IO;
import static com.seldon.news.common.app.di.ApplicationDomainModule.APP_DOMAIN_UI;

@Singleton
@Component(modules = {ApplicationDomainModule.class, ApplicationDataModule.class})
public interface ApplicationComponent {
    void inject(NewsApplication application);

    @Named(APP_DOMAIN_IO) Scheduler provideIO();
    @Named(APP_DOMAIN_UI) Scheduler provideUI();
    Retrofit provideRetrofit();

    Context provideContext();
    UserEntity provideUser();
    Preferences providePreferences();
    UserInteractor provideUserSaver();
}
