package com.seldon.news.screens.menu.di;


import com.seldon.news.common.app.di.Activity;
import com.seldon.news.common.app.di.ApplicationComponent;
import com.seldon.news.screens.menu.ui.MenuFragment;

import dagger.Component;

@Activity
@Component(dependencies = ApplicationComponent.class, modules = {MenuUIModule.class, MenuDomainModule.class})
public interface MenuComponent {
    void inject(MenuFragment fragment);
}
