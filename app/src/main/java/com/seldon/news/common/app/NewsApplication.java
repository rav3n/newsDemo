package com.seldon.news.common.app;

import android.app.Application;

import com.seldon.news.common.app.di.ApplicationComponent;
import com.seldon.news.common.app.di.ApplicationDataModule;
import com.seldon.news.common.app.di.DaggerApplicationComponent;

public class NewsApplication extends Application {
    private static ApplicationComponent component;

    @Override public void onCreate() {
        super.onCreate();
        buildComponent();
    }

    public void buildComponent() {
        component = DaggerApplicationComponent.builder()
                .applicationDataModule(new ApplicationDataModule(this))
                .build();
        component.inject(this);
    }

    public static ApplicationComponent getComponent() {
        return component;
    }
}
