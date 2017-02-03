package com.seldon.news.screens.menu.ui.news;

import android.view.View;
import android.widget.TextView;

import com.seldon.news.R;
import com.seldon.news.common.rubrics.data.RubricEntity;
import com.seldon.news.screens.menu.ui.MenuPage;
import com.seldon.news.screens.menu.ui.MenuPageCallback;

import fw.v6.core.utils.V6DebugLogger;

public class NewsPage extends MenuPage {

    public NewsPage(MenuPageCallback callback) {
        super(callback);
    }

    @Override
    protected View createPagerView() {
        final RubricsViewModel viewModel = new RubricsViewModel(
                R.string.menu_spinner_home,
                R.string.menu_spinner_all_rubrics,
                R.string.menu_spinner_user_rubrics,
                callback.getRubricsModel().generalRubrics,
                callback.getRubricsModel().userRubrics,
                new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        V6DebugLogger.d("go to home");
                    }
                },
                new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        RubricEntity entity = (RubricEntity) v.getTag();
                        V6DebugLogger.d("go to " + entity.getName());
                    }
                });

        TextView tv = new TextView(callback.getContext());
        tv.setText("news view");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                RubricsBuilder.showDialog(callback.getContext(), viewModel);
            }
        });
        return tv;
    }
}
