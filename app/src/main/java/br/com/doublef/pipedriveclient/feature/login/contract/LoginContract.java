package br.com.doublef.pipedriveclient.feature.login.contract;


import br.com.doublef.pipedriveclient.base.MvpView;
import br.com.doublef.pipedriveclient.model.ResponseUserData;
import br.com.doublef.pipedriveclient.model.User;

public interface LoginContract extends MvpView {

    interface LoginView extends MvpView {

        void login();
        void autoLogin();

        void onLoginSuccess(ResponseUserData responseUser);
        void showLoginError(String error);

        void showProgressBar();
        void hideProgressBar();

        void enableLoginButton();
        void disableLoginButton();

    }

    interface LoginPresenter {
        void login(User user);
        void attemptLogin();
    }

}
