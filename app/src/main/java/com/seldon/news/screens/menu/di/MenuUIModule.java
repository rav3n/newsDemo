package com.seldon.news.screens.menu.di;

import android.view.View;
import android.webkit.WebView;

import com.seldon.news.R;
import com.seldon.news.common.views.BottomNavigationView;
import com.seldon.news.screens.menu.ui.MenuRouter;
import com.seldon.news.screens.menu.ui.MenuView;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Module;
import dagger.Provides;

@Module
public class MenuUIModule {

    private MenuView viewInterface;
    private MenuRouter routerInterface;

    @BindView(R.id.content_menu_web_view)
    protected WebView webView;

    @BindView(R.id.content_menu_bottom_navigation)
    protected BottomNavigationView navigationMenu;

    public MenuUIModule(View viewContainer, MenuView viewInterface, MenuRouter routerInterface) {
        this.viewInterface = viewInterface;
        this.routerInterface = routerInterface;
        ButterKnife.bind(this, viewContainer);
    }

    @Provides public MenuView providerView() {
        return viewInterface;
    }

    @Provides public MenuRouter providerRouter() {
        return routerInterface;
    }

    @Provides
    BottomNavigationView provideMenu() {
        return navigationMenu;
    }

    @Provides WebView provideWebView() {
        return webView;
    }
}
