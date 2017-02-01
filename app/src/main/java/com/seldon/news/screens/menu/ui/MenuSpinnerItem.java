package com.seldon.news.screens.menu.ui;

import android.view.View;

public class MenuSpinnerItem {

    private boolean isHeader;
    private String title;
    private View.OnClickListener listener;

    public MenuSpinnerItem(String title) {
        this.title = title;
        isHeader = true;
    }

    public MenuSpinnerItem(String title, View.OnClickListener listener) {
        this.listener = listener;
        this.title = title;
        isHeader = false;
    }

    public boolean isHeader() {
        return isHeader ;
    }

    public String getTitle() {
        return title;
    }

    public View.OnClickListener getListener() {
        return listener;
    }
}
