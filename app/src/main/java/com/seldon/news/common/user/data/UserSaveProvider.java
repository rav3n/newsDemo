package com.seldon.news.common.user.data;

public interface UserSaveProvider {
    void saveCredentials(String login, String password);
}
