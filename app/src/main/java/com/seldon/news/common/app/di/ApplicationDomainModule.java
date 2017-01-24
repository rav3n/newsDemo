package com.seldon.news.common.app.di;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class ApplicationDomainModule {

    public static final String APP_DOMAIN_IO = "app_domain_io";
    public static final String APP_DOMAIN_UI = "app_domain_ui";

    @Provides @Named(APP_DOMAIN_IO)
    public Scheduler provideIO() {
        return Schedulers.io();
    }

    @Provides @Named(APP_DOMAIN_UI)
    public Scheduler provideUI() {
        return AndroidSchedulers.mainThread();
    }
}
