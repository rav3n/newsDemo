package com.seldon.news.screens.auth.data;

public class AuthRequestEntity {
    private String name;
    private String password;
    private boolean rememberMe;

    public AuthRequestEntity(String name, String password, boolean rememberMe) {
        this.name = name;
        this.password = password;
        this.rememberMe = rememberMe;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }
}
