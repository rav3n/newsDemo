package com.seldon.news.screens.auth.di;

import android.view.View;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.seldon.news.R;
import com.seldon.news.screens.auth.data.AuthRequestEntity;
import com.seldon.news.screens.auth.domain.AuthSendInteractor;
import com.seldon.news.screens.auth.ui.AuthDataToRequestMapper;
import com.seldon.news.screens.auth.ui.AuthRouter;
import com.seldon.news.screens.auth.ui.AuthSendPresenter;
import com.seldon.news.screens.auth.ui.AuthView;

import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.Module;
import dagger.Provides;
import rx.Observable;
import rx.Scheduler;

import static com.seldon.news.common.app.di.ApplicationDomainModule.APP_DOMAIN_IO;
import static com.seldon.news.common.app.di.ApplicationDomainModule.APP_DOMAIN_UI;

@Module
public class AuthUIModule {

    public static final String AUTH_UI_NAME = "auth_ui_name";
    public static final String AUTH_UI_PASSWORD = "auth_ui_password";

    /**
     * В данном модуле будет провайдиться все что касается вьюшки, поля, кнопки, сам интефейс
     * Ты спросишь нахера?
     * Для инкапсуляции, дальше увидишь как я это использую в тестках и в сборке самого экрана
     */

    private AuthView viewInterface;
    private AuthRouter routerInterface;

    @BindView(R.id.fragment_auth_name)
    protected EditText name;

    @BindView(R.id.fragment_auth_password)
    protected EditText password;

    public AuthUIModule(View viewContainer, AuthView viewInterface, AuthRouter routerInterface) {
        this.viewInterface = viewInterface;
        this.routerInterface = routerInterface;
        ButterKnife.bind(this, viewContainer);
    }

    @Provides public AuthSendPresenter provideSendPresenter(
            /**
             * Вот именно тут начинается вся магия даггера,
             *  даггер будет бегать по графу зависимостей и
             *  искать совкадения по типу и сам подставит в
             *  этот метод, добавляется в граф аннотацией @Provide
             */
            AuthView view,
            AuthRouter router,
            AuthSendInteractor interactor,
            @Named(APP_DOMAIN_UI) Scheduler ui) {
        return new AuthSendPresenter(view, router, interactor, ui);
    }

    @Provides public Observable<AuthRequestEntity> provideObservableRequest(
            @Named(AUTH_UI_NAME) EditText name,
            @Named(AUTH_UI_PASSWORD) EditText password,
            @Named(APP_DOMAIN_IO) Scheduler io,
            @Named(APP_DOMAIN_UI) Scheduler ui,
            AuthDataToRequestMapper dataToRequestMapper) {
        return Observable.combineLatest(RxTextView.textChanges(name),
                                        RxTextView.textChanges(password),
                                        dataToRequestMapper)
                .subscribeOn(ui)
                .observeOn(io);
    }

    @Provides public AuthDataToRequestMapper provideRequestToResponseMapper() {
        return new AuthDataToRequestMapper();
    }

    @Provides public AuthView provideView() {
        return viewInterface;
    }

    @Provides public AuthRouter provideRouter() {
        return routerInterface;
    }

    @Provides @Named(AUTH_UI_NAME)
    public EditText provideName() {
        return name;
    }

    @Provides @Named(AUTH_UI_PASSWORD)
    public EditText providePassword() {
        return password;
    }

    @OnClick(R.id.fragment_auth_send)
    protected void onButtonSendClickListener() {
        viewInterface.onSendButtonAction();
    }
}
