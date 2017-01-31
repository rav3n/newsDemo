package com.seldon.news.screens.auth.domain;

import com.seldon.news.common.base.domain.mapper.NewsResponseMapper;
import com.seldon.news.screens.auth.data.AuthApiProvider;
import com.seldon.news.screens.auth.data.AuthRequestEntity;
import com.seldon.news.screens.auth.data.AuthResponseEntity;

import rx.Observable;

public class AuthLoginInteractor {

    private AuthApiProvider provider;

    public AuthLoginInteractor(AuthApiProvider provider) {
        this.provider = provider;
    }

    public Observable<AuthResponseEntity> getResponse(AuthRequestEntity request) {
        return provider.login(request.getName(), request.getPassword(), request.isRememberMe())
            .map(new NewsResponseMapper<AuthResponseEntity>());
    }
}
