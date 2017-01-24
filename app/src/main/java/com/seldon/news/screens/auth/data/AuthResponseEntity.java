package com.seldon.news.screens.auth.data;

public class AuthResponseEntity {

    private int code;
    private String message;

    public AuthResponseEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
