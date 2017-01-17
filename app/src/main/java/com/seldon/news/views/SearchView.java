package com.seldon.news.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.seldon.news.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

public class SearchView extends FrameLayout {

    @BindView(R.id.search_string)
    protected EditText searchText;

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
                String target = searchText.getText() + "";
                subscriber.onNext(target);
            }
        });
        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (v != null) {
                        InputMethodManager imm = (InputMethodManager) getContext()
                                .getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                    String target = searchText.getText() + "";
                    subscriber.onNext(target);
                    return true;
                }
                return false;
            }
        });
        return RxTextView.textChanges(searchText)
                .debounce(3, TimeUnit.SECONDS)
                .filter(new Func1<CharSequence, Boolean>() {
                    @Override public Boolean call(CharSequence charSequence) {
                        return charSequence.length() > 1;
                    }
                })
                .map(new Func1<CharSequence, String>() {
                    @Override public String call(CharSequence charSequence) {
                        return charSequence.toString();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public String getSearchTarget()  {
        return searchText.getText().toString();
    }

}
