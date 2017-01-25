package com.seldon.news.screens.auth.data;

public class AuthResponseEntity {

    private String token;
    private String returnUrl;

    public AuthResponseEntity() {}

    public String getToken() {
        return token;
    }

    public String getReturnUrl() {
        return returnUrl;
    }
}
