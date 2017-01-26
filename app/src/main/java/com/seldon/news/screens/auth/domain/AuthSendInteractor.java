package com.seldon.news.screens.auth.domain;

import com.seldon.news.common.app.NewsException;
import com.seldon.news.common.base.data.NewsResponse;
import com.seldon.news.screens.auth.data.AuthApiProvider;
import com.seldon.news.screens.auth.data.AuthRequestEntity;
import com.seldon.news.screens.auth.data.AuthResponseEntity;

import rx.Observable;
import rx.functions.Func1;

public class AuthSendInteractor {

    private AuthApiProvider provider;

    public AuthSendInteractor(AuthApiProvider provider) {
        this.provider = provider;
    }

    public Observable<AuthResponseEntity> getResponse(AuthRequestEntity request) {
        return provider.login(request.getName(), request.getPassword(), request.isRememberMe())
            .map(new Func1<NewsResponse<AuthResponseEntity>, AuthResponseEntity>() {
                @Override public AuthResponseEntity call(NewsResponse<AuthResponseEntity> responseEntity) {
                    if (!responseEntity.isSuccessful()) {
                        throw new NewsException(responseEntity.getResult(), responseEntity.getError());
                    }
                    return responseEntity.getResponse();
                }
        });
    }
}
