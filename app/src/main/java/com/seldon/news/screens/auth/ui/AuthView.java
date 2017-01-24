package com.seldon.news.screens.auth.ui;

public interface AuthView {
    public void showToast(String message);
    public void onSendButtonAction();
    public void enableProgressDialog(boolean enable);
}
