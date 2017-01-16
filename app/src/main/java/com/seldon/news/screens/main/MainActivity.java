package com.seldon.news.screens.main;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.seldon.news.R;
import com.seldon.news.screens.search.data.SearchStringEntity;
import com.seldon.news.screens.search.ui.SearchActivity;
import com.seldon.news.utils.IntentWrapper;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final int REQUEST_CODE = 123;

    private Unbinder unbinder;

    @BindView(R.id.search_string)
    protected TextView searchTextView;

    @BindView(R.id.web_view)
    protected WebView webView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        unbinder = ButterKnife.bind(this, this);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.search_string)
    @Override public void onSearchClick() {
        SearchActivity.startForResult(this, getSearchTarget(), REQUEST_CODE);
    }

    @Override public void setSearchText(String text) {
        searchTextView.setText(text);
    }

    /**
     * пилим запрос с прилетевшим параметром поиска
     * @param param
     */
    @Override public void pushParamsToWeb(String param) {
        //TODO: webView.addJavascriptInterface(...);
    }

    @Override protected void onActivityResult(int requestCode,
                                              int resultCode,
                                              Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                setSearchText(IntentWrapper.getString(data));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String getSearchTarget() {
        return searchTextView.getText().toString();
    }
}
