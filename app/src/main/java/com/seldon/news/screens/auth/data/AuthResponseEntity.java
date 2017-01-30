package com.seldon.news.screens.auth.data;

import com.seldon.news.common.user.data.UserProfile;

public class AuthResponseEntity {

    private String token;
    private String returnUrl;
    private UserProfile profile;

    public AuthResponseEntity() {}

    public String getToken() {
        return token;
    }

    public String getReturnUrl() {
        return returnUrl;
    }
}
