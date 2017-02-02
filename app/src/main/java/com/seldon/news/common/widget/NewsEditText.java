package com.seldon.news.common.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import com.seldon.news.common.utils.FontUtils;

public class NewsEditText extends AppCompatEditText {

    public NewsEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        FontUtils.applyFontStyleAttrs(this, attrs);
    }

    public NewsEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        FontUtils.applyFontStyleAttrs(this, attrs);
    }

    public NewsEditText(Context context) {
        this(context, null, 0);
    }
}
