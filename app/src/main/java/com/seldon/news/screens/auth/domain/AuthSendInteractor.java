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
    private Observable<AuthRequestEntity> observableRequest;

    public AuthSendInteractor(AuthApiProvider provider,
                              Observable<AuthRequestEntity> observableRequest) {
        this.provider = provider;
        this.observableRequest = observableRequest;
    }

    public Observable<AuthResponseEntity> getResponse() {
        return observableRequest
            .flatMap(new Func1<AuthRequestEntity, Observable<NewsResponse<AuthResponseEntity>>>() {
                @Override
                public Observable<NewsResponse<AuthResponseEntity>> call(AuthRequestEntity request) {
                    return provider.login(request.getName(), request.getPassword(), request.isRememberMe());
                }
            })
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
