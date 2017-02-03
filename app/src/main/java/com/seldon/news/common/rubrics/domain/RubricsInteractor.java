package com.seldon.news.common.rubrics.domain;

import com.seldon.news.common.base.data.NewsException;
import com.seldon.news.common.rubrics.data.ApiRubricsProvider;
import com.seldon.news.common.rubrics.data.RubricEntity;
import com.seldon.news.common.rubrics.data.RubricsResponse;

import rx.Observable;
import rx.functions.Func1;

public class RubricsInteractor {

    private ApiRubricsProvider provider;

    public RubricsInteractor(ApiRubricsProvider provider) {
        this.provider = provider;
    }

    public Observable<RubricEntity[]> fetchRubrics() {
        return provider.getRubrics().map(new ResponseMapper());
    }

    public Observable<RubricEntity[]> fetchCustomUserRubrics() {
        return provider.getCustomUserRubric().map(new ResponseMapper());
    }

    public static class ResponseMapper implements Func1<RubricsResponse, RubricEntity[]> {
        @Override public RubricEntity[] call(RubricsResponse response) {
            if (!response.isSuccess()) {
                throw new NewsException(0, "error");
            }
            return response.getRubrics();
        }
    }
}
