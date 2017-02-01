package com.seldon.news.screens.menu.domain;

import android.support.annotation.Nullable;
import android.support.v4.util.DebugUtils;
import android.view.View;

import com.seldon.news.R;
import com.seldon.news.common.rubrics.data.RubricEntity;
import com.seldon.news.common.rubrics.domain.RubricsInteractor;
import com.seldon.news.screens.menu.ui.MenuRouter;
import com.seldon.news.screens.menu.ui.MenuView;
import com.seldon.news.screens.menu.ui.MenuViewModel;

import fw.v6.core.domain.V6BasePresenter;
import fw.v6.core.utils.V6DebugLogger;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func2;
import rx.subscriptions.CompositeSubscription;

public class MenuRubricsPresenter extends V6BasePresenter<MenuView, MenuRouter> {

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    private RubricsInteractor rubricsInteractor;
    private Scheduler io;
    private Scheduler ui;

    public MenuRubricsPresenter(@Nullable MenuView menuView,
                                @Nullable MenuRouter router,
                                Scheduler io,
                                Scheduler ui,
                                RubricsInteractor rubricsInteractor) {
        super(menuView, router);
        this.rubricsInteractor = rubricsInteractor;
        this.io = io;
        this.ui = ui;
    }

    @Override public void onCreate() {}

    @Override public void onDestroy() {
        unregisterSubscriptions();
    }

    public void loadAllRubrics() {
        registerSubscription(Observable.combineLatest(
                rubricsInteractor.fetchRubrics(),
                rubricsInteractor.fetchCustomUserRubrics(),
                new Func2<RubricEntity[], RubricEntity[], MenuViewModel>() {
                    @Override public MenuViewModel call(RubricEntity[] allRubrics, RubricEntity[] userRubrics) {
                        return new MenuViewModel(
                                R.string.menu_spinner_home,
                                R.string.menu_spinner_all_rubrics,
                                R.string.menu_spinner_user_rubrics,
                                allRubrics,
                                userRubrics,
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
                    }
            })
            .observeOn(io)
            .subscribeOn(ui)
            .subscribe(new Subscriber<MenuViewModel>() {
                @Override public void onCompleted() {}
                @Override public void onError(Throwable e) {
                    e.printStackTrace();
                    getView().showError(e.getMessage());
                }

                @Override public void onNext(MenuViewModel viewModel) {
                    getView().showMenuSpinner(viewModel);
                }
            })
        );
    }

    private void registerSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    private void unregisterSubscriptions() {
        compositeSubscription.unsubscribe();
    }
}
