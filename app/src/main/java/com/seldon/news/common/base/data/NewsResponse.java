package com.seldon.news.common.base.data;

public class NewsResponse<T> {

    private int result;
    private String error;
    private T response;

    public NewsResponse(T response) {
        result = 200;
        error = "";
        this.response = response;
    }

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
