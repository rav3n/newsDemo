package com.seldon.news.screens.main;

import com.seldon.news.screens.search.data.SearchStringEntity;

public interface MainView {
    void onSearchClick();
    void setSearchText(String text);
    void pushParamsToWeb(String param);
}
