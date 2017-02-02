package com.seldon.news.screens.menu.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.seldon.news.R;
import com.seldon.news.common.Const;
import com.seldon.news.common.app.NewsApplication;
import com.seldon.news.common.views.BottomNavigationView;
import com.seldon.news.screens.menu.di.DaggerMenuComponent;
import com.seldon.news.screens.menu.di.MenuUIModule;
import com.seldon.news.screens.menu.domain.MenuRubricsPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MenuFragment extends Fragment implements MenuView {

    @Inject
    protected WebView webView;

    @Inject
    protected MenuRubricsPresenter rubricsPresenter;

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_menu, container, false);
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inject();
        initWeb();
        rubricsPresenter.loadAllRubrics();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        rubricsPresenter.onDestroy();
    }

    private void initWeb() {
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(Const.SERVER_URL, "testtest");

        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(Const.SERVER_URL);
    }

    private void inject() {
        DaggerMenuComponent.builder()
                .applicationComponent(NewsApplication.getComponent())
                .menuUIModule(new MenuUIModule(getView(), this, new MenuRouter(getActivity())))
                .build()
                .inject(this);
    }

    @Override public void showPage(String url) {
        webView.loadUrl(url);
    }

    private TextView createTemp(String s) {
        TextView tv = new TextView(getActivity());
        tv.setText(s);
        return tv;
    }

    @Override public void showMenuSpinner(MenuViewModel viewModel) {
        final ViewPager pager = (ViewPager) getView().findViewById(R.id.content_menu_view_pager);
        final List<View> views = new ArrayList<>();
        views.add(createTemp("home view"));
        views.add(createTemp("search view"));
        views.add(createTemp("profile view"));
        pager.setAdapter(new PagerAdapter() {

            @Override public Object instantiateItem(ViewGroup container, int position) {
                View view = views.get(position);
                container.addView(view);
                return view;
            }

            @Override public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override public int getCount() {
                return views.size();
            }

            @Override public boolean isViewFromObject(View view, Object object) {
                return view.equals(object);
            }
        });
        BottomNavigationView navigationView = (BottomNavigationView) getView().findViewById(R.id.content_menu_bottom_navigation);
        navigationView.setOnItemClickListener(new BottomNavigationView.NewsBottomNavigationListener() {
            @Override public void onItemClickListener(View item) {
                switch (item.getId()) {
                    case R.id.bottom_navigation_home:
                        pager.setCurrentItem(0, false);
                        break;
                    case R.id.bottom_navigation_search:
                        pager.setCurrentItem(1, false);
                        break;
                    case R.id.bottom_navigation_profile:
                        pager.setCurrentItem(2, false);
                        break;
                }
            }
        });
        /*
        Spinner spinner = (Spinner) getActivity().findViewById(R.id.toolbar_new_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            private boolean first = true;

            @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!first) {
                    MenuSpinnerItem item = (MenuSpinnerItem) parent.getAdapter().getItem(position);
                    Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_LONG).show();
                }
                first = false;
            }

            @Override public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner.setAdapter(viewModel.getSpinnerAdapter(getActivity()));
        */
    }

    @Override public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}
