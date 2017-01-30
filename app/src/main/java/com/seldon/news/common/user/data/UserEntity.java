package com.seldon.news.common.user.data;

import java.io.Serializable;

public class UserEntity implements Serializable {

    private String login;
    private String password;

    private String token;

    public void setCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public boolean isAuthorized() {
        return token != null;
    }

    public void onAuthorized(String token) {
        this.token = token;
    }

    public void logout() {
        this.password = null;
        this.token = null;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

//    public UserProfile getProfile() {
//        return profile;
//    }
}
