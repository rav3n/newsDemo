package com.seldon.news.screens.auth.data;

import rx.Observable;

public interface AuthApiProvider {
    /*
    /Account/Login?Email=&Password=&RememberMe=true авторизация
     */
    Observable<AuthResponseEntity> login(String name, String password, boolean rememberMe);
}
