package com.seldon.news.common.utils;

import com.seldon.news.common.base.data.NewsPreferences;

import java.io.IOException;
import java.util.Set;

import fw.v6.core.utils.V6DebugLogger;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {

    private NewsPreferences preferences;

    public AddCookiesInterceptor(NewsPreferences preferences) {
        this.preferences = preferences;
    }

    @Override public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        Set<String> cookie = preferences.getCookie();
        if (cookie != null) {
            for (String data : cookie) {
                builder.addHeader("Cookie", data);
                V6DebugLogger.d("Adding Header: " + data);
            }
        }
        return chain.proceed(builder.build());
    }
}
