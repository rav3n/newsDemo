package com.seldon.news.screens.menu.ui;

import android.view.View;
public abstract class MenuPage {

    protected MenuPageCallback callback;
    View pagerView;

    public MenuPage(MenuPageCallback callback) {
        this.callback = callback;
    }

    public View getPagerView() {
        if (pagerView == null) {
            pagerView = createPagerView();
        }
        return pagerView;
    }

    protected abstract View createPagerView();

    public boolean overrideDeviceBack() {
        return false;
    }
}
