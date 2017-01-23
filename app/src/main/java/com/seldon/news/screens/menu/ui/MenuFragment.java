package com.seldon.news.screens.menu.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.seldon.news.R;
import com.seldon.news.common.views.NewsBottomNavigation;

public class MenuFragment extends Fragment {

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_menu, container, false);
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NewsBottomNavigation bottomNavigation = (NewsBottomNavigation) view
                .findViewById(R.id.content_menu_bottom_navigation);
        bottomNavigation.setOnItemClickListener(new NewsBottomNavigation.NewsBottomNavigationListener() {
            @Override public void onItemClickListener(View item) {
                TextView tv = (TextView) getView().findViewById(R.id.content_menu_text_view);
                tv.setText(((Button) item).getText());
            }
        });
    }
}
