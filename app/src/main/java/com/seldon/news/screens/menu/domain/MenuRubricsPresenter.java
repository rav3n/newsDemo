package com.seldon.news.screens.menu.domain;

import android.support.annotation.Nullable;

import com.seldon.news.common.rubrics.data.RubricEntity;
import com.seldon.news.common.rubrics.domain.RubricsInteractor;
import com.seldon.news.screens.menu.data.AllRubricsModel;
import com.seldon.news.screens.menu.ui.MenuRouter;
import com.seldon.news.screens.menu.ui.MenuView;

import fw.v6.core.domain.V6BasePresenter;
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
                new Func2<RubricEntity[], RubricEntity[], AllRubricsModel>() {
                    @Override public AllRubricsModel call(RubricEntity[] allRubrics, RubricEntity[] userRubrics) {
                        return new AllRubricsModel(allRubrics, userRubrics);
                    }
            })
            .observeOn(io)
            .subscribeOn(ui)
            .subscribe(new Subscriber<AllRubricsModel>() {
                @Override public void onCompleted() {}
                @Override public void onError(Throwable e) {
                    e.printStackTrace();
                    getView().showError(e.getMessage());
                }

                @Override public void onNext(AllRubricsModel model) {
                    getView().onRubricsLoaded(model);
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
