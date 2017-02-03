package com.seldon.news.common.rubrics.data;

import retrofit2.http.GET;
import rx.Observable;

public interface ApiRubricsProvider {

    @GET("api/UsersActionApi/GetRubrics")
    public Observable<RubricsResponse> getRubrics();

    @GET("api/UsersActionApi/GetCustomUserRubric")
    public Observable<RubricsResponse> getCustomUserRubric();
}
