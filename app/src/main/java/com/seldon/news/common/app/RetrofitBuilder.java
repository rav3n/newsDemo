package com.seldon.news.common.app;

import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

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

    public static Retrofit build(String baseUrl) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        if (ENABLE_LOG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            clientBuilder.addInterceptor(logging);
        }

        clientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Content-Type", "application/json; charset=utf-8")
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
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
