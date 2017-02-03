package com.seldon.news.screens.menu.ui.news;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.seldon.news.R;
import com.seldon.news.screens.menu.ui.news.RubricsViewModel;

public class RubricsBuilder {
    public static Dialog showDialog(Context context,
                                    RubricsViewModel viewModel) {
        Dialog dialog = new Dialog(context);
//        Dialog dialog = new Dialog(context, R.style.Style);
        dialog.setContentView(createView(context, viewModel));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        View applyButton = filterView.findViewById(R.id.card_filter_dialog_apply);
//        applyButton.setOnClickListener(v -> {
//            dialog.dismiss();
//            onApply.onClick(v);
//        });

        initLayoutParams(dialog);
        dialog.show();
        return dialog;
    }

    private static View createView(Context context, RubricsViewModel menuViewModel) {
        View view = LayoutInflater.from(context).inflate(R.layout.menu_rubrics_view, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.menu_rubrics_recyler);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(menuViewModel.getRecyclerAdapter(context));
        return view;
    }

    private static void initLayoutParams(Dialog dialog) {
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
    }
}
