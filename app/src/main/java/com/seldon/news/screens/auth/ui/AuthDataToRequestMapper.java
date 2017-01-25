package com.seldon.news.screens.auth.ui;

import com.seldon.news.screens.auth.data.AuthRequestEntity;

import fw.v6.core.utils.V6DebugLogger;
import rx.functions.Func2;

public class AuthDataToRequestMapper implements Func2<CharSequence, CharSequence, AuthRequestEntity> {
    @Override public AuthRequestEntity call(CharSequence name, CharSequence password) {
        return new AuthRequestEntity(name.toString(), password.toString(), true);
    }
}
