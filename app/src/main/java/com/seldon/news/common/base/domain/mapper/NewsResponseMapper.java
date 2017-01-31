package com.seldon.news.common.base.domain.mapper;

import com.seldon.news.common.base.data.NewsException;
import com.seldon.news.common.base.data.NewsResponse;

import rx.functions.Func1;

public class NewsResponseMapper<T> implements Func1<NewsResponse<T>, T> {
    @Override public T call(NewsResponse<T> responseEntity) {
        if (!responseEntity.isSuccessful()) {
            throw new NewsException(responseEntity.getResult(), responseEntity.getError());
        }
        return responseEntity.getResponse();
    }
}
