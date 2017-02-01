package com.seldon.news.screens.menu.ui;

import android.content.Context;
import android.widget.SpinnerAdapter;

public interface MenuView {
    void showPage(String url);
    void showMenuSpinner(SpinnerAdapter adapter);
    void showError(String message);
    Context getContext();
}
