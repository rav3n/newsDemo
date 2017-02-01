package com.seldon.news.common.rubrics.data;

import com.seldon.news.common.base.data.NewsResponse;

import retrofit2.http.GET;
import rx.Observable;

public interface ApiRubricsProvider {

    @GET("api/UsersActionApi/GetRubrics")
    public Observable<NewsResponse<RubricEntity[]>> getRubrics();

    @GET("api/UsersActionApi/GetCustomUserRubric")
    public Observable<NewsResponse<RubricEntity[]>> getCustomUserRubric();
}
