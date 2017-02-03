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
import com.seldon.news.common.rubrics.data.RubricEntity;
import com.seldon.news.common.views.BottomNavigationView;
import com.seldon.news.screens.menu.data.AllRubricsModel;
import com.seldon.news.screens.menu.di.DaggerMenuComponent;
import com.seldon.news.screens.menu.di.MenuUIModule;
import com.seldon.news.screens.menu.domain.MenuRubricsPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import fw.v6.core.utils.V6DebugLogger;

public class MenuFragment extends Fragment implements MenuView {

    @Inject
    protected WebView webView;
    @Inject
    BottomNavigationView navigationView;
    @Inject
    ViewPager viewPager;

    @Inject
    protected MenuRubricsPresenter rubricsPresenter;

    private AllRubricsModel rubricsModel;
    private List<MenuPage> pages = new ArrayList<>();

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

    private void inject() {
        DaggerMenuComponent.builder()
                .applicationComponent(NewsApplication.getComponent())
                .menuUIModule(new MenuUIModule(getView(), this, new MenuRouter(getActivity())))
                .build()
                .inject(this);
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

    @Override public void showPage(String url) {
        webView.loadUrl(url);
    }

    private View firstView(String s, final RubricsViewModel viewModel) {
        TextView tv = new TextView(getActivity());
        tv.setText(s);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                RubricsBuilder.showDialog(getActivity(), viewModel);
            }
        });
        return tv;
    }

    @Override public void onRubricsLoaded(AllRubricsModel model) {
        this.rubricsModel = model;
        initNavigation();
    }

    @Override public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    private void initNavigation() {
        pages.clear();
        pages.add(new NewsPage());
        pages.add(new SearchPage());
        pages.add(new ProfilePage());

        viewPager.setAdapter(new PagerAdapter() {

            @Override public Object instantiateItem(ViewGroup container, int position) {
                View view = pages.get(position).getPagerView();
                container.addView(view);
                return view;
            }

            @Override public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override public int getCount() {
                return pages.size();
            }

            @Override public boolean isViewFromObject(View view, Object object) {
                return view.equals(object);
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

        navigationView.setOnItemClickListener(new BottomNavigationView.NewsBottomNavigationListener() {
            @Override public void onItemClickListener(View item) {
                switch (item.getId()) {
                    case R.id.bottom_navigation_home:
                        setCurrentTab(0);
                        break;
                    case R.id.bottom_navigation_search:
                        setCurrentTab(1);
                        break;
                    case R.id.bottom_navigation_profile:
                        setCurrentTab(2);
                        break;
                }
            }
        });
    }

    private void setCurrentTab(int index) {
        viewPager.setCurrentItem(index, false);
    }

    abstract class MenuPage {

        View pagerView;

        View getPagerView() {
            if (pagerView == null) {
                pagerView = createPagerView();
            }
            return pagerView;
        }

        abstract View createPagerView();
    }

    class NewsPage extends MenuPage {

        @Override
        View createPagerView() {
            final RubricsViewModel viewModel = new RubricsViewModel(
                    R.string.menu_spinner_home,
                    R.string.menu_spinner_all_rubrics,
                    R.string.menu_spinner_user_rubrics,
                    rubricsModel.generalRubrics,
                    rubricsModel.userRubrics,
                    new View.OnClickListener() {
                        @Override public void onClick(View v) {
                            V6DebugLogger.d("go to home");
                        }
                    },
                    new View.OnClickListener() {
                        @Override public void onClick(View v) {
                            RubricEntity entity = (RubricEntity) v.getTag();
                            V6DebugLogger.d("go to " + entity.getName());
                        }
                    });

            TextView tv = new TextView(getActivity());
            tv.setText("news view");
            tv.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    RubricsBuilder.showDialog(getActivity(), viewModel);
                }
            });
            return tv;
        }
    }

    class SearchPage extends MenuPage {

        @Override
        View createPagerView() {
            TextView tv = new TextView(getActivity());
            tv.setText("search view");
            return tv;
        }
    }

    class ProfilePage extends MenuPage {

        @Override
        View createPagerView() {
            TextView tv = new TextView(getActivity());
            tv.setText("profile view");
            return tv;
        }
    }
}
