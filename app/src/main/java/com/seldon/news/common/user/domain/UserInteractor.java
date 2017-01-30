package com.seldon.news.common.user.domain;

import com.seldon.news.common.base.data.NewsPreferences;
import com.seldon.news.common.user.data.UserEntity;
import com.seldon.news.common.user.data.UserProfile;

public class UserInteractor {

    private NewsPreferences newsPreferences;
    private UserEntity userEntity;

    public UserInteractor(NewsPreferences newsPreferences, UserEntity userEntity) {
        this.newsPreferences = newsPreferences;
        this.userEntity = userEntity;
    }

    public void saveCredentials(String login, String password) {
        userEntity.setCredentials(login, password);
        newsPreferences.setUserLogin(login);
        newsPreferences.setUserPassword(password);
    }

    public void onAuthorized(String token) {
        userEntity.onAuthorized(token);
    }
}
