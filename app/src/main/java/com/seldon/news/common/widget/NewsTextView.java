package com.seldon.news.common.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.seldon.news.common.utils.FontUtils;

public class NewsTextView extends AppCompatTextView {

    public NewsTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        FontUtils.applyFontStyleAttrs(this, attrs);
    }

    public NewsTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NewsTextView(Context context) {
        this(context, null, 0);
    }
}
