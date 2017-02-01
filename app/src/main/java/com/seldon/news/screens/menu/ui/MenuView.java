package com.seldon.news.screens.menu.ui;

public interface MenuView {
    void showPage(String url);
    void showMenuSpinner(MenuViewModel viewModel);
    void showError(String message);
}
