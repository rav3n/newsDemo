package com.seldon.news.common.app.di;

import android.content.Context;

import com.seldon.news.common.base.data.NewsPreferences;
import com.seldon.news.common.app.RetrofitBuilder;
import com.seldon.news.common.user.data.UserEntity;
import com.seldon.news.common.user.domain.UserInteractor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

import static com.seldon.news.common.Const.SERVER_URL;
import static com.seldon.news.common.Const.PREFERENCES_COMMON;

@Module
public class ApplicationDataModule {

    private Context context;

    public ApplicationDataModule(Context context) {
        this.context = context;
    }

    @Singleton @Provides public Retrofit provideRetrofit(NewsPreferences newsPreferences) {
        return RetrofitBuilder.build(SERVER_URL, newsPreferences);
    }

    @Provides public Context provideContext() {
        return context;
    }

    @Singleton @Provides public UserEntity provideUser(NewsPreferences newsPreferences) {
        UserEntity user = new UserEntity();
        String login = newsPreferences.getUserLogin();
        String password = newsPreferences.getUserPassword();
        user.setCredentials(login, password);
        return user;
    }

    @Singleton @Provides
    public UserInteractor provideUserSaver(final NewsPreferences newsPreferences, final UserEntity user) {
        return new UserInteractor(newsPreferences, user);
    }

    @Singleton @Provides public NewsPreferences providePreferences(Context context) {
        return new NewsPreferences(context, PREFERENCES_COMMON);
    }
}
