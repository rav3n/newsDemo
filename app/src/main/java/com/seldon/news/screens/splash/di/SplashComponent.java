package com.seldon.news.screens.splash.di;

import com.seldon.news.common.app.di.Activity;
import com.seldon.news.common.app.di.ApplicationComponent;
import com.seldon.news.screens.auth.di.AuthDataModule;
import com.seldon.news.screens.splash.ui.SplashActivity;

import dagger.Component;

@Activity
@Component(dependencies = ApplicationComponent.class, modules = {AuthDataModule.class, SplashUIModule.class})
public interface SplashComponent {
    void inject(SplashActivity activity);
}
