package com.seldon.news.screens.menu.ui;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.seldon.news.R;
import com.seldon.news.common.rubrics.data.RubricEntity;

import java.util.ArrayList;
import java.util.List;

public class MenuViewModel {
    /**
    - Главная
    - "Рубрики" (просто тайтл)
            - <общие рубрики>
    - "Пользовательские" (просто тайтл)
            - <пользовательские рубрики>
    */

    private int homeTitleResId;
    private int allRubricsTitleResId;
    private int userRubricsTitleResId;

    private RubricEntity[] allRubrics;
    private RubricEntity[] userRubrics;

    private View.OnClickListener homeClickListener;
    private View.OnClickListener rubricsClickListener;

    public MenuViewModel(int homeTitleResId,
                         int allRubricsTitleResId,
                         int userRubricsTitleResId,
                         RubricEntity[] allRubrics,
                         RubricEntity[] userRubrics,
                         View.OnClickListener homeClickListener,
                         View.OnClickListener rubricsClickListener) {
        this.homeTitleResId = homeTitleResId;
        this.allRubricsTitleResId = allRubricsTitleResId;
        this.userRubricsTitleResId = userRubricsTitleResId;
        this.allRubrics = allRubrics;
        this.userRubrics = userRubrics;
        this.homeClickListener = homeClickListener;
        this.rubricsClickListener = rubricsClickListener;
    }

    private void putAllRubrics(List<MenuSpinnerItem> items, RubricEntity[] rubricEntities) {
        for(int i = 0; i < rubricEntities.length; i++) {
            final RubricEntity entity = rubricEntities[i];
            items.add(new MenuSpinnerItem(entity.getName(), new View.OnClickListener() {
                @Override public void onClick(View v) {
                    v.setTag(entity);
                    rubricsClickListener.onClick(v);
                }
            }));
        }
    }

    public SpinnerAdapter getSpinnerAdapter(Context context) {
        List<MenuSpinnerItem> items = new ArrayList<>();
        items.add(new MenuSpinnerItem(context.getString(homeTitleResId), homeClickListener));
        items.add(new MenuSpinnerItem(context.getString(allRubricsTitleResId)));
        putAllRubrics(items, allRubrics);
        items.add(new MenuSpinnerItem(context.getString(userRubricsTitleResId)));
        putAllRubrics(items, userRubrics);
        return new MenuSpinnerAdapter(context, items);
    }

    public class MenuSpinnerAdapter implements SpinnerAdapter {

        private final int TYPE_SIMPLE = R.layout.menu_spinner_simple_item;
        private final int TYPE_HEADER = R.layout.menu_spinner_header_item;

        private Context context;
        private List<MenuSpinnerItem> items;

        public MenuSpinnerAdapter(Context context, List<MenuSpinnerItem> items) {
            this.context = context;
            this.items = items;
        }

        @Override public View getDropDownView(int position, View convertView, ViewGroup parent) {
            int type = getItemViewType(position);
            TextView tv = (TextView) LayoutInflater.from(context).inflate(type, parent, false);
            MenuSpinnerItem item = getItem(position);
            tv.setText(item.getTitle());
            if (type == TYPE_HEADER) {
                tv.setOnClickListener(null);
            }
            return tv;
        }

        @Override public void registerDataSetObserver(DataSetObserver observer) {}
        @Override public void unregisterDataSetObserver(DataSetObserver observer) {}

        @Override public int getCount() {
            return items.size();
        }

        @Override public MenuSpinnerItem getItem(int position) {
            return items.get(position);
        }

        @Override public long getItemId(int position) {
            return 0;
        }

        @Override public boolean hasStableIds() {
            return true;
        }

        @Override public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(context);
            tv.setText(getItem(position).getTitle());
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            return tv;
        }

        @Override public int getItemViewType(int position) {
            MenuSpinnerItem item = getItem(position);
            if (item.isHeader()) {
                return TYPE_HEADER;
            }
            return TYPE_SIMPLE;
        }

        @Override public int getViewTypeCount() {
            return 1;
        }

        @Override public boolean isEmpty() {
            return false;
        }
    }
}
