package com.seldon.news.common.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.seldon.news.R;

public class NewsBottomNavigation extends LinearLayout {

    public interface NewsBottomNavigationListener {
        void onItemClickListener(View item);
    }

    private NewsBottomNavigationListener delegate;

    public NewsBottomNavigation(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.news_bottom_navigation, this, true);
        init();
    }

    private void init() {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.setOnClickListener(new OnClickListener() {
                @Override public void onClick(View v) {
                    if (delegate != null) {
                        delegate.onItemClickListener(v);
                    }
                }
            });
        }
    }

    public void setOnItemClickListener(NewsBottomNavigationListener litener) {
        delegate = litener;
    }
}
