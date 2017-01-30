package com.seldon.news.common.user.domain;

import com.seldon.news.common.base.data.Preferences;
import com.seldon.news.common.user.data.UserEntity;

public class UserInteractor {

    private Preferences preferences;
    private UserEntity userEntity;

    public UserInteractor(Preferences preferences, UserEntity userEntity) {
        this.preferences = preferences;
        this.userEntity = userEntity;
    }

    public void saveCredentials(String login, String password) {
        userEntity.setCredentials(login, password);
        preferences.setUserLogin(login);
        preferences.setUserPassword(password);
    }
}
