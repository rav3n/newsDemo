package com.seldon.news.common.app;

public class NewsException extends Error {
    private int code;
    private String message;

    public NewsException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override public String getMessage() {
        return message;
    }
}
