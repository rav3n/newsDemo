package com.seldon.news.screens.search.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.seldon.news.R;
import com.seldon.news.screens.search.data.MockSearchProvider;
import com.seldon.news.screens.search.data.SearchStringEntity;
import com.seldon.news.screens.search.domain.SearchPresenter;
import com.seldon.news.utils.IntentWrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.crypto.spec.DESedeKeySpec;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import fw.v6.core.ui.base.V6BaseFragment;
import fw.v6.core.utils.DebugLogger;

public class SearchFragment extends V6BaseFragment<List<SearchStringEntity>> {

    private static String SEARCH_ARG = "search_arg";
    private FrameLayout container;
    private Unbinder unbinder;
    private SearchPresenter presenter;

    @BindView(R.id.recycler)
    protected RecyclerView recycler;

    @BindView(R.id.search_string)
    protected EditText searchEditText;

    public static Bundle createFragmentBundle(String searchTarget) {
        Bundle bundle = new Bundle();
        bundle.putString(SEARCH_ARG, searchTarget);
        return bundle;
    }

    @Override protected void onScreenCreate(FrameLayout container) {
        this.container = container;
        LayoutInflater.from(getActivity()).inflate(R.layout.fragment_search, container, true);
        unbinder = ButterKnife.bind(this, this.container);
        initRecycler();
        /**
         * эта хрень долбиться в интернет по нашем параметрам, конкретная реализация
         * отдает зашлуку в ввиде скиска случайных хэш кодов
         */
        MockSearchProvider provider = new MockSearchProvider();
        presenter = new SearchPresenter(this, null, searchEditText, provider);
        presenter.onCreate();
        searchEditText.setText(getSearchTargetFromArguments());
        searchEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    setActivityResultAndFinish(searchEditText.getText().toString());
                }
                return false;
            }
        });
    }

    private void initRecycler() {
        SearchAdapter adapter = new SearchAdapter(new ArrayList<SearchStringEntity>());
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(adapter);
    }

    private String getSearchTargetFromArguments() {
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(SEARCH_ARG)) {
            return bundle.getString(SEARCH_ARG);
        }
        return "";
    }

    @Override protected void onScreenDestroy() {
        unbinder.unbind();
        presenter.onDestroy();
    }

    @OnClick(R.id.search_button)
    public void doSearch() {
        setActivityResultAndFinish(searchEditText.getText().toString());
    }

    @Override public void renderContent(List<SearchStringEntity> searchStringEntity) {
        SearchAdapter adapter = (SearchAdapter) recycler.getAdapter();
        adapter.updateAndNotify(searchStringEntity);
    }

    private class SearchAdapter extends RecyclerView.Adapter<SearchRowHolder> {

        private List<SearchStringEntity> items;

        public SearchAdapter(List<SearchStringEntity> items) {
            this.items = items;
        }

        public void updateAndNotify(Collection<SearchStringEntity> collection) {
            items.clear();
            items.addAll(collection);
            notifyDataSetChanged();
        }

        @Override public SearchRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SearchRowHolder(LayoutInflater.from(getActivity())
                    .inflate(R.layout.search_row, parent, false));
        }

        @Override public void onBindViewHolder(SearchRowHolder holder, int position) {
            SearchStringEntity entity = items.get(position);
            holder.bind(entity);
        }

        @Override public int getItemCount() {
            return items.size();
        }
    }

    private class SearchRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public SearchRowHolder(View view) {
            super(view);
        }

        public void bind(final SearchStringEntity entity) {
            TextView textView = (TextView) itemView.findViewById(R.id.text_view);
            textView.setText(entity.getString());
            textView.setOnClickListener(this);
        }

        @Override public void onClick(View v) {
            TextView textView = (TextView) v;
            setActivityResultAndFinish(textView.getText().toString());
        }
    }

    private void setActivityResultAndFinish(String string) {
        Intent intent = getActivity().getIntent();
        IntentWrapper.putString(intent, string);
        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }
}
