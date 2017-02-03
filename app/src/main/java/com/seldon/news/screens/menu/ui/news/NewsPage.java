package com.seldon.news.screens.menu.ui.news;

import android.view.View;
import android.widget.TextView;

import com.seldon.news.common.rubrics.data.RubricEntity;
import com.seldon.news.screens.menu.ui.MenuPage;
import com.seldon.news.screens.menu.ui.MenuPageCallback;

import fw.v6.core.utils.V6DebugLogger;

public class NewsPage extends MenuPage implements RubricSelectListener {

    public NewsPage(MenuPageCallback callback) {
        super(callback);
    }

    @Override
    protected View createPagerView() {
        final RubricsViewModel viewModel = new RubricsViewModel(callback.getRubricsModel(), this);

        TextView tv = new TextView(callback.getContext());
        tv.setText("news view");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                RubricsBuilder.showDialog(callback.getContext(), viewModel);
            }
        });
        return tv;
    }

    @Override
    public void onRubricSelect(RubricEntity rubric) {
        V6DebugLogger.d("onRubricSelect " + rubric.getName());
    }

    @Override
    public void onFavoritesSelect() {
        V6DebugLogger.d("onFavoritesSelect");
    }

    @Override
    public void onFutureReadSelect() {
        V6DebugLogger.d("onFutureReadSelect");
    }
}
