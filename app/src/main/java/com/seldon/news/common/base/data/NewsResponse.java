package com.seldon.news.common.base.data;

public class NewsResponse<T> {

    private int result;
    private String error;
    private T response;

    public int getResult() {
        return result;
    }

    public String getError() {
        return error;
    }

    public T getResponse() {
        return response;
    }

    public boolean isSuccessful() {
        return result == 200;
    }
}
