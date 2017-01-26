package com.seldon.news.common.app.di;

import android.content.Context;

import com.seldon.news.common.app.Preferences;
import com.seldon.news.common.app.RetrofitBuilder;
import com.seldon.news.common.user.data.UserEntity;

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

    @Singleton @Provides public Retrofit provideRetrofit() {
        return RetrofitBuilder.build(SERVER_URL);
    }

    @Provides public Context provideContext() {
        return context;
    }

    @Singleton @Provides public UserEntity provideUser(Preferences preferences) {
        String login = preferences.getUserLogin();
        String password = preferences.getUserPassword();
        return new UserEntity(login, password);
    }

    @Singleton @Provides public Preferences providePreferences(Context context) {
        return new Preferences(context, PREFERENCES_COMMON);
    }
}
