package com.seldon.news.screens.auth.ui;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.seldon.news.R;
import com.seldon.news.common.app.NewsApplication;
import com.seldon.news.screens.auth.di.AuthUIModule;
import com.seldon.news.screens.auth.di.DaggerAuthComponent;

import javax.inject.Inject;
import javax.inject.Named;

import static com.seldon.news.screens.auth.di.AuthUIModule.AUTH_UI_BUTTON;

public class AuthFragment extends Fragment implements AuthView {

    @Inject
    protected AuthLoginPresenter presenter;
    @Inject
    protected AuthValidationPresenter validation;

    @Inject @Named(AUTH_UI_BUTTON)
    protected View button;

    private ProgressDialog progressDialog;

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(getActivity());
        return inflater.inflate(R.layout.fragment_auth, container, false);
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inject();
        validation.onCreate();
    }

    /**
     * тут даггер собирает всю шубуру
     */
    private void inject() {
        DaggerAuthComponent.builder()
                .applicationComponent(NewsApplication.getComponent())
                .authUIModule(new AuthUIModule(getView(), this, new AuthRouter(getActivity())))
                .build()
                .inject(this);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
        validation.onDestroy();
    }

    @Override public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override public void onSendButtonAction() {
        presenter.send();
    }

    @Override public void enableProgressDialog(boolean enable) {
        if (enable) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }

    @Override public void enableButton(boolean enable) {
        button.setEnabled(enable);
    }
}
