package com.seldon.news.screens.auth.data;

import com.seldon.news.common.base.data.NewsResponse;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface AuthApiProvider {
    @POST("/ru/Account/Login")
    Observable<NewsResponse<AuthResponseEntity>> login(@Query("Email") String name,
                                                       @Query("Password") String password,
                                                       @Query("RememberMe") boolean rememberMe);
}
