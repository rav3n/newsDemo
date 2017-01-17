package com.seldon.news.screens.main;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.seldon.news.R;
import com.seldon.news.utils.IntentWrapper;
import com.seldon.news.views.SearchView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fw.v6.core.utils.DebugLogger;
import rx.Subscriber;
import rx.Subscription;

import static com.seldon.news.Const.SEARCH_URL;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final int REQUEST_CODE = 123;

    private Unbinder unbinder;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.search_string)
    protected TextView searchTextView;

    @BindView(R.id.web_view)
    protected WebView webView;

    private Subscription subscription;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        unbinder = ButterKnife.bind(this, this);
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        SearchView searchView = (SearchView) toolbar.findViewById(R.id.search_view);
        subscription = searchView.subscribe(new Subscriber() {
            @Override public void onCompleted() {}

            @Override public void onError(Throwable e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override public void onNext(Object o) {
                pushParamsToWeb(o.toString());
            }
        });
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

//    @OnClick(R.id.search_string)
    @Override public void onSearchClick() {
//        SearchActivity.startForResult(this, getSearchTarget(), REQUEST_CODE);
    }

    @Override public void setSearchText(String text) {
        searchTextView.setText(text);
    }

    /**
     * пилим запрос с прилетевшим параметром поиска
     * @param param
     */
    @Override public void pushParamsToWeb(String param) {
        DebugLogger.d("search target is " + param);
        webView.loadUrl(SEARCH_URL + param);
    }

    @Override protected void onActivityResult(int requestCode,
                                              int resultCode,
                                              Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String target = IntentWrapper.getString(data);
                setSearchText(target);
                pushParamsToWeb(target);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String getSearchTarget() {
        return searchTextView.getText().toString();
    }
}
