package com.seldon.news.screens.auth.di;

import com.seldon.news.common.app.di.Activity;
import com.seldon.news.common.app.di.ApplicationComponent;
import com.seldon.news.screens.auth.ui.AuthFragment;

import dagger.Component;

@Activity
@Component(dependencies = ApplicationComponent.class, modules = {AuthUIModule.class,
        AuthDomainModule.class, AuthDataModule.class})
public interface AuthComponent {
    void inject(AuthFragment fragment);
}
