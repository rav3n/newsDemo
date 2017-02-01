package com.seldon.news.common.rubrics.domain;

import com.seldon.news.common.base.data.NewsException;
import com.seldon.news.common.base.data.NewsResponse;
import com.seldon.news.common.rubrics.data.ApiRubricsProvider;
import com.seldon.news.common.rubrics.data.RubricEntity;

import org.junit.Test;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RubricsInteractorTest {

    @Test public void fetchRubricsTest() {
        NewsResponse<RubricEntity[]> mockResponse = mock(NewsResponse.class);
        RubricEntity[] mockEntity = new RubricEntity[]{ mock(RubricEntity.class) };
        ApiRubricsProvider provider = mock(ApiRubricsProvider.class);

        when(provider.getRubrics()).thenReturn(Observable.just(mockResponse));
        when(mockResponse.isSuccessful()).thenReturn(true);
        when(mockResponse.getResponse()).thenReturn(mockEntity);

        RubricsInteractor interactor = new RubricsInteractor(provider);

        TestSubscriber subscriber = new TestSubscriber();
        interactor.fetchRubrics().subscribe(subscriber);
        subscriber.assertNoErrors();
        subscriber.assertValue(mockEntity);
    }

    @Test public void fetchCustomUserRubricsTest() {
        NewsResponse<RubricEntity[]> mockResponse = mock(NewsResponse.class);
        RubricEntity[] mockEntity = new RubricEntity[]{ mock(RubricEntity.class) };
        ApiRubricsProvider provider = mock(ApiRubricsProvider.class);

        when(provider.getCustomUserRubric()).thenReturn(Observable.just(mockResponse));
        when(mockResponse.isSuccessful()).thenReturn(true);
        when(mockResponse.getResponse()).thenReturn(mockEntity);

        RubricsInteractor interactor = new RubricsInteractor(provider);

        TestSubscriber subscriber = new TestSubscriber();
        interactor.fetchCustomUserRubrics().subscribe(subscriber);
        subscriber.assertNoErrors();
        subscriber.assertValue(mockEntity);
    }

    @Test public void fetchRubricsErrorTest() {
        NewsResponse<RubricEntity[]> mockResponse = mock(NewsResponse.class);
        RubricEntity[] mockEntity = new RubricEntity[]{mock(RubricEntity.class)};
        ApiRubricsProvider provider = mock(ApiRubricsProvider.class);

        when(provider.getRubrics()).thenReturn(Observable.just(mockResponse));
        when(mockResponse.isSuccessful()).thenReturn(false);
        when(mockResponse.getResult()).thenReturn(0);
        when(mockResponse.getResponse()).thenReturn(mockEntity);

        RubricsInteractor interactor = new RubricsInteractor(provider);
        TestSubscriber subscriber = new TestSubscriber();
        interactor.fetchRubrics().subscribe(subscriber);
        subscriber.assertError(NewsException.class);
    }

    @Test public void fetchCustomUserRubricsErrorTest() {
        NewsResponse<RubricEntity[]> mockResponse = mock(NewsResponse.class);
        RubricEntity[] mockEntity = new RubricEntity[]{mock(RubricEntity.class)};
        ApiRubricsProvider provider = mock(ApiRubricsProvider.class);

        when(provider.getCustomUserRubric()).thenReturn(Observable.just(mockResponse));
        when(mockResponse.isSuccessful()).thenReturn(false);
        when(mockResponse.getResult()).thenReturn(0);
        when(mockResponse.getResponse()).thenReturn(mockEntity);

        RubricsInteractor interactor = new RubricsInteractor(provider);

        TestSubscriber subscriber = new TestSubscriber();
        interactor.fetchCustomUserRubrics().subscribe(subscriber);
        subscriber.assertError(NewsException.class);
    }
}
