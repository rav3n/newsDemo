package com.seldon.news.screens.menu.ui.news;

import android.view.View;
import android.widget.TextView;

import com.seldon.news.R;
import com.seldon.news.common.rubrics.data.RubricEntity;
import com.seldon.news.screens.menu.ui.MenuPage;
import com.seldon.news.screens.menu.ui.MenuPageCallback;

import fw.v6.core.utils.V6DebugLogger;

public class NewsPage extends MenuPage implements RubricSelectListener {

    private TextView header;
    private RubricEntity currentRubric;

    public NewsPage(MenuPageCallback callback) {
        super(callback);
        currentRubric = callback.getRubricsModel().getGeneralRubrics()[0];
    }

    @Override
    protected View createPagerView() {
        final RubricsViewModel viewModel = new RubricsViewModel(callback.getRubricsModel(), this);

        final String dialogTitle = callback.getContext().getString(R.string.rubrics_title);
        header = new TextView(callback.getContext());
        header.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                updateHeader(dialogTitle);
                RubricsBuilder.showDialog(callback.getContext(), viewModel);
            }
        });
        updateHeader(currentRubric.getName());
        return header;
    }

    @Override
    public void onRubricSelect(RubricEntity rubric) {
        V6DebugLogger.d("onRubricSelect " + rubric.getName());
        currentRubric = rubric;
        updateHeader(currentRubric.getName());
    }

    private void updateHeader(String title) {
        header.setText(title);
    }
}
