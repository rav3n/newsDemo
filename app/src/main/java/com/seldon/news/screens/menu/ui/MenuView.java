package com.seldon.news.screens.menu.ui;

import com.seldon.news.screens.menu.data.AllRubricsModel;

public interface MenuView {
    void showPage(String url);
    void onRubricsLoaded(AllRubricsModel model);
    void showError(String message);
}
