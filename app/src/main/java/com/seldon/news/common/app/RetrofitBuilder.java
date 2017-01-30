package com.seldon.news.common.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.seldon.news.common.base.data.NewsPreferences;
import com.seldon.news.common.utils.AddCookiesInterceptor;
import com.seldon.news.common.utils.ReceivedCookiesInterceptor;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import okhttp3.Connection;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    public static final boolean ENABLE_LOG = true;

    public static Retrofit build(String baseUrl, NewsPreferences preferences) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        if (ENABLE_LOG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(logging);
        }

        clientBuilder.addInterceptor(new AddCookiesInterceptor(preferences));
        clientBuilder.addInterceptor(new ReceivedCookiesInterceptor(preferences));
        clientBuilder.addInterceptor(new Interceptor() {
            @Override public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Content-Type", "application/json; charset=utf-8")
                        .build();
                Response response = chain.proceed(request);
                return response;
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit;
    }

    public static Gson createGson() {
        GsonBuilder gson = new GsonBuilder();
//        gson.registerTypeAdapter(V6ErrorEntity.class, new ErrorAdapter());
        gson.setLenient();
        return gson.create();
    }

}
