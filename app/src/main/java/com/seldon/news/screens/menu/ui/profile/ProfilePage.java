package com.seldon.news.screens.menu.ui.profile;

import android.view.View;
import android.widget.TextView;

import com.seldon.news.screens.menu.ui.MenuPage;
import com.seldon.news.screens.menu.ui.MenuPageCallback;

public class ProfilePage extends MenuPage {

    public ProfilePage(MenuPageCallback callback) {
        super(callback);
    }

    @Override
    protected View createPagerView() {
        TextView tv = new TextView(callback.getContext());
        tv.setText("ProfilePage");
        return tv;
    }
}
