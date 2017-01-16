package com.seldon.news.screens.search.domain;

import android.support.annotation.Nullable;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.seldon.news.screens.search.data.SearchProvider;
import com.seldon.news.screens.search.data.SearchStringEntity;

import java.util.List;
import java.util.concurrent.TimeUnit;

import fw.v6.core.domain.V6BasePresenter;
import fw.v6.core.domain.V6EventBusSender;
import fw.v6.core.ui.V6SimpleErrorFragment;
import fw.v6.core.ui.base.V6BaseView;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

public class SearchPresenter extends V6BasePresenter<V6BaseView, Void> {

    private Observable<CharSequence> observableSearch;
    private SearchProvider provider;

    private Subscription subscription;

    public SearchPresenter(V6BaseView v6BaseView,
                           @Nullable Void aVoid,
                           EditText searchEditText,
                           SearchProvider provider) {
        super(v6BaseView, aVoid);
        this.provider = provider;
        createObservable(searchEditText);
    }

    private void createObservable(EditText editText) {
        observableSearch = RxTextView.textChanges(editText);
    }

    @Override public void onCreate() {
        getView().setVisibilityProgressBar(false);

        subscription = observableSearch
            .debounce(1, TimeUnit.SECONDS)
            .map(new Func1<CharSequence, String>() {
                @Override public String call(CharSequence charSequence) {
                    return charSequence.toString();
                }
            })
            .flatMap(new Func1<String, Observable<List<SearchStringEntity>>>() {
                @Override public Observable<List<SearchStringEntity>> call(String target) {
                    return provider.search(target);
                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<List<SearchStringEntity>>() {
                @Override public void onCompleted() {}

                @Override public void onError(Throwable e) {
                    e.printStackTrace();
                    V6EventBusSender.toError(V6SimpleErrorFragment.createBundleError(e.getMessage()));
                }

                @Override public void onNext(List<SearchStringEntity> searchStringEntities) {
                    getView().renderContent(searchStringEntities);
                }
            });
    }

    @Override public void onDestroy() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }
}
