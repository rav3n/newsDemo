package com.seldon.news.screens.auth.ui;

public interface AuthView {
    void showToast(String message);
    void onSendButtonAction();
    void enableProgressDialog(boolean enable);
    void enableButton(boolean enable);
}
