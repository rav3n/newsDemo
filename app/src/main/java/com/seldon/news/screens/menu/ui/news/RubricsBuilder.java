package com.seldon.news.screens.menu.ui.news;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.seldon.news.R;

public class RubricsBuilder {

    public static Dialog showDialog(final Context context, final RubricsViewModel viewModel) {
        Dialog dialog = new Dialog(context, R.style.FullScreenDialog);
        dialog.setContentView(createView(context, viewModel));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initLayoutParams(dialog);

        dialog.setOnKeyListener(new Dialog.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (viewModel.onBackPressed(context)) {
                        return true;
                    }
                }
                return false;
            }
        });

        dialog.show();
        return dialog;
    }

    private static View createView(Context context, RubricsViewModel menuViewModel) {
        View view = LayoutInflater.from(context).inflate(R.layout.menu_rubrics_dialog, null);
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
