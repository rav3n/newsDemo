package com.seldon.news.common.rubrics.domain;

import com.seldon.news.common.base.domain.mapper.NewsResponseMapper;
import com.seldon.news.common.rubrics.data.ApiRubricsProvider;
import com.seldon.news.common.rubrics.data.RubricEntity;

import rx.Observable;

public class RubricsInteractor {

    private ApiRubricsProvider provider;

    public RubricsInteractor(ApiRubricsProvider provider) {
        this.provider = provider;
    }

    public Observable<RubricEntity> fetchRubrics() {
        return provider.getRubrics().map(new NewsResponseMapper<RubricEntity>());
    }

    public Observable<RubricEntity> fetchCustomUserRubrics() {
        return provider.getCustomUserRubric().map(new NewsResponseMapper<RubricEntity>());
    }
}
