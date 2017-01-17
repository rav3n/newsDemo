package com.seldon.news.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TimeUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.seldon.news.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;

public class SearchView extends FrameLayout {

    @BindView(R.id.search_string)
    protected TextView searchTextView;

    @BindView(R.id.search_button)
    protected View searchButton;

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.search_view, this, true);
        ButterKnife.bind(this, this);
    }

    public Subscription subscribe(final Subscriber subscriber) {
        searchButton.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View v) {
                subscriber.onNext(searchTextView.getText().toString());
            }
        });
        return RxTextView.textChanges(searchTextView)
                .debounce(1, TimeUnit.SECONDS)
                .filter(new Func1<CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence charSequence) {
                        return charSequence.length() > 1;
                    }
                })
                .subscribe(subscriber);

    }

    public String getSearchTarget()  {
        return searchTextView.getText().toString();
    }

}
