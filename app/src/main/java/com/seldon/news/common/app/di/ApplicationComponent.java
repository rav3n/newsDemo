package com.seldon.news.common.app.di;

import com.seldon.news.common.app.NewsApplication;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import rx.Scheduler;

import static com.seldon.news.common.app.di.ApplicationDomainModule.APP_DOMAIN_IO;
import static com.seldon.news.common.app.di.ApplicationDomainModule.APP_DOMAIN_UI;

@Singleton
@Component(modules = ApplicationDomainModule.class)
public interface ApplicationComponent {
    void inject(NewsApplication application);

    @Named(APP_DOMAIN_IO) Scheduler provideIO();
    @Named(APP_DOMAIN_UI) Scheduler provideUI();
}
