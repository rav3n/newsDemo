package com.seldon.news.common.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.seldon.news.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomNavigationItemView extends FrameLayout {

    @BindView(R.id.navigation_item_icon)
    protected ImageView icon;
    @BindView(R.id.navigation_item_text)
    protected TextView title;

    public BottomNavigationItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.bottom_navigation_item, this, true);
        ButterKnife.bind(this);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NavigationItem);
        try {
            title.setText(a.getString(R.styleable.NavigationItem_title));
            icon.setImageResource(a.getResourceId(R.styleable.NavigationItem_icon, 0));
        } finally {
            a.recycle();
        }
    }
}
