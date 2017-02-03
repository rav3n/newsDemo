package com.seldon.news.screens.menu.ui.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.seldon.news.R;
import com.seldon.news.common.rubrics.data.RubricEntity;
import com.seldon.news.screens.menu.data.AllRubricsModel;

import java.util.ArrayList;
import java.util.List;

public class RubricsViewModel {

    private AllRubricsModel model;

    private RubricSelectListener clickListener;
    private RecyclerView.Adapter adapter;
    private List<RecyclerItem> items;
    private boolean subMenu;

    public RubricsViewModel(AllRubricsModel model,
                            RubricSelectListener clickListener) {
        this.model = model;
        this.clickListener = clickListener;
    }

    public RecyclerView.Adapter getRecyclerAdapter(Context context) {
        items = new ArrayList<>();
        showMain(context);
        adapter = new MenuRecyclerAdapter(context, items);
        return adapter;
    }

    private RecyclerItem createRubricItem(final RubricEntity rubric) {
        return new RecyclerItem(rubric.getName(), TYPE_RUBRIC, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onRubricSelect(rubric);
            }
        });
    }

    private void showMain(final Context context) {
        items.clear();
        items.add(createRubricItem(model.getReadInFutureRubric()));
        items.add(createRubricItem(model.getFavoritesRubric()));
        items.add(new RecyclerItem(context.getString(R.string.rubrics_custom), TYPE_SUB, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSub(context);
                subMenu = true;
                adapter.notifyDataSetChanged();
            }
        }));
        items.add(new RecyclerItem(context.getString(R.string.rubrics_general), TYPE_HEADER, null));
        for (RubricEntity rubric : model.getGeneralRubrics()) {
            items.add(createRubricItem(rubric));
        }
    }

    private void showSub(Context context) {
        items.clear();
        items.add(new RecyclerItem(context.getString(R.string.rubrics_custom), TYPE_HEADER, null));
        for (RubricEntity rubric : model.getUserRubrics()) {
            items.add(createRubricItem(rubric));
        }
    }

    boolean onBackPressed(Context context) {
        if (subMenu) {
            showMain(context);
            adapter.notifyDataSetChanged();
            subMenu = false;
            return true;
        }
        return false;
    }

    private static final int TYPE_RUBRIC = R.layout.menu_rubrics_dialog_item_rubric;
    private static final int TYPE_SUB = R.layout.menu_rubrics_dialog_item_sub;
    private static final int TYPE_HEADER = R.layout.menu_rubrics_dialog_item_header;

    private class MenuRecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

        private Context context;
        private List<RecyclerItem> items;

        MenuRecyclerAdapter(Context context, List<RecyclerItem> items) {
            this.context = context;
            this.items = items;
        }

        @Override public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecyclerViewHolder(LayoutInflater.from(context).inflate(viewType, parent, false)){};
        }

        @Override public void onBindViewHolder(RecyclerViewHolder holder, int position) {
            final RecyclerItem item = getItem(position);
            holder.setTitle(item.title);
            holder.itemView.setOnClickListener(item.listener);
        }

        @Override public int getItemCount() {
            return items.size();
        }

        public RecyclerItem getItem(int position) {
            return items.get(position);
        }

        @Override public int getItemViewType(int position) {
            return getItem(position).type;
        }
    }

    private class RecyclerItem {

        final String title;
        final int type;
        final View.OnClickListener listener;

        RecyclerItem(String title, int type, View.OnClickListener listener) {
            this.title = title;
            this.type = type;
            this.listener = listener;
        }
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.menu_rubrics_dialog_item_title);
        }

        void setTitle(String text) {
            title.setText(text);
        }
    }
}
