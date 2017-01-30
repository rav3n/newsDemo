package com.seldon.news.common.utils;

import com.seldon.news.common.base.data.NewsPreferences;

import java.io.IOException;
import java.util.HashSet;

import fw.v6.core.utils.V6DebugLogger;
import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {

    private NewsPreferences preferences;

    public ReceivedCookiesInterceptor(NewsPreferences preferences) {
        this.preferences = preferences;
    }

    @Override public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        if (!response.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookie = new HashSet<>();
            for (String data : response.headers("Set-Cookie")) {
                cookie.add(data);
                V6DebugLogger.d("Set-Cookie: " + data);
            }
            preferences.setCookie(cookie);
        }
        return response;
    }
}
