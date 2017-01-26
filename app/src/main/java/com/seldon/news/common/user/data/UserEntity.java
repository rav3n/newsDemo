package com.seldon.news.common.user.data;

import java.io.Serializable;

public class UserEntity implements Serializable {

    private String login;
    private String password;

    private UserProfile profile;

    public UserEntity(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public boolean isAuthorized() {
        return profile != null;
    }

    public void onAuthorized(UserProfile profile) {
        this.profile = profile;
    }

    public void logout() {
        this.password = null;
        this.profile = null;
    }
}
