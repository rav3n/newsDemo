package com.seldon.news.common.rubrics.domain;

import com.seldon.news.common.base.data.NewsException;
import com.seldon.news.common.base.data.NewsResponse;
import com.seldon.news.common.rubrics.data.ApiRubricsProvider;
import com.seldon.news.common.rubrics.data.RubricEntity;
import com.seldon.news.common.rubrics.data.RubricsResponse;

import org.junit.Test;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RubricsInteractorTest {

    @Test public void fetchRubricsTest() {
        RubricsResponse mockResponse = mock(RubricsResponse.class);
        RubricEntity[] mockEntity = new RubricEntity[]{ mock(RubricEntity.class) };
        ApiRubricsProvider provider = mock(ApiRubricsProvider.class);

        when(provider.getRubrics()).thenReturn(Observable.just(mockResponse));
        when(mockResponse.isSuccess()).thenReturn(true);
        when(mockResponse.getRubrics()).thenReturn(mockEntity);

        RubricsInteractor interactor = new RubricsInteractor(provider);

        TestSubscriber subscriber = new TestSubscriber();
        interactor.fetchRubrics().subscribe(subscriber);
        subscriber.assertNoErrors();
        subscriber.assertValue(mockEntity);
    }

    @Test public void fetchCustomUserRubricsTest() {
        RubricsResponse mockResponse = mock(RubricsResponse.class);
        RubricEntity[] mockEntity = new RubricEntity[]{ mock(RubricEntity.class) };
        ApiRubricsProvider provider = mock(ApiRubricsProvider.class);

        when(provider.getCustomUserRubric()).thenReturn(Observable.just(mockResponse));
        when(mockResponse.isSuccess()).thenReturn(true);
        when(mockResponse.getRubrics()).thenReturn(mockEntity);

        RubricsInteractor interactor = new RubricsInteractor(provider);

        TestSubscriber subscriber = new TestSubscriber();
        interactor.fetchCustomUserRubrics().subscribe(subscriber);
        subscriber.assertNoErrors();
        subscriber.assertValue(mockEntity);
    }

    @Test public void fetchRubricsErrorTest() {
        RubricsResponse mockResponse = mock(RubricsResponse.class);
        ApiRubricsProvider provider = mock(ApiRubricsProvider.class);

        when(provider.getRubrics()).thenReturn(Observable.just(mockResponse));
        when(mockResponse.isSuccess()).thenReturn(false);

        RubricsInteractor interactor = new RubricsInteractor(provider);
        TestSubscriber subscriber = new TestSubscriber();
        interactor.fetchRubrics().subscribe(subscriber);
        subscriber.assertError(NewsException.class);
    }

    @Test public void fetchCustomUserRubricsErrorTest() {
        RubricsResponse mockResponse = mock(RubricsResponse.class);
        ApiRubricsProvider provider = mock(ApiRubricsProvider.class);

        when(provider.getCustomUserRubric()).thenReturn(Observable.just(mockResponse));
        when(mockResponse.isSuccess()).thenReturn(false);

        RubricsInteractor interactor = new RubricsInteractor(provider);

        TestSubscriber subscriber = new TestSubscriber();
        interactor.fetchCustomUserRubrics().subscribe(subscriber);
        subscriber.assertError(NewsException.class);
    }
}
