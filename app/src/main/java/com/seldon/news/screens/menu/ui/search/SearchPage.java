package com.seldon.news.screens.menu.ui.search;

import android.view.View;
import android.widget.TextView;

import com.seldon.news.screens.menu.ui.MenuPage;
import com.seldon.news.screens.menu.ui.MenuPageCallback;

public class SearchPage extends MenuPage {

    public SearchPage(MenuPageCallback callback) {
        super(callback);
    }

    @Override
    protected View createPagerView() {
        TextView tv = new TextView(callback.getContext());
        tv.setText("SearchPage");
        return tv;
    }
}
