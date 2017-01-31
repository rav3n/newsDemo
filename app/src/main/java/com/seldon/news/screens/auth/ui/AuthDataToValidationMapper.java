package com.seldon.news.screens.auth.ui;

import rx.functions.Func2;

public class AuthDataToValidationMapper implements Func2<CharSequence, CharSequence, Boolean> {
    @Override public Boolean call(CharSequence name, CharSequence password) {
        return name.length() > 0 && password.length() > 0 ? Boolean.TRUE : Boolean.FALSE;
    }
}
