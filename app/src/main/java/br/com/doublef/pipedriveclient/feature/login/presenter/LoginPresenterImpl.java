package br.com.doublef.pipedriveclient.feature.login.presenter;


import javax.inject.Inject;

import br.com.doublef.pipedriveclient.base.BasePresenter;
import br.com.doublef.pipedriveclient.datamanager.DataManager;
import br.com.doublef.pipedriveclient.feature.login.contract.LoginContract;
import br.com.doublef.pipedriveclient.model.ResponseUser;
import br.com.doublef.pipedriveclient.model.ResponseUserData;
import br.com.doublef.pipedriveclient.model.User;
import br.com.doublef.pipedriveclient.remote.RetrofitException;
import br.com.doublef.pipedriveclient.remote.RxUnsubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginPresenterImpl extends BasePresenter<LoginContract.LoginView>
implements LoginContract.LoginPresenter{

    private final DataManager dataManager;

    private Subscription subscription;

    @Inject
    public LoginPresenterImpl(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(LoginContract.LoginView view) {
        super.attachView(view);
        RxUnsubscriber.unsubscribe(subscription);
    }

    @Override
    public void login(User user) {
        doBeforeSubscribe();
        RxUnsubscriber.unsubscribe(subscription);
        subscription = this.dataManager.login(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onLoginSuccess, this::onLoginError);
    }

    @Override
    public void attemptLogin() {
        ResponseUser responseUser = dataManager.getLoggedUser();
        if ( responseUser != null && !responseUser.getApiToken().isEmpty() ) {
            getView().autoLogin();
        }else {
            getView().login();
        }
    }

    private void doBeforeSubscribe() {
        getView().showProgressBar();
        getView().disableLoginButton();
    }

    private void onLoginError(Throwable e) {
        RetrofitException exception = (RetrofitException) e;
        getView().showLoginError(exception.toString());
        getView().hideProgressBar();
        getView().enableLoginButton();
    }

    private void onLoginSuccess(ResponseUserData personData) {
        saveLoggedUser(personData);
        getView().onLoginSuccess(personData);
        getView().hideProgressBar();
        getView().enableLoginButton();
    }

    private void saveLoggedUser(ResponseUserData responseUserData) {
        ResponseUser responseUser = responseUserData.getData().get(0);
        dataManager.saveLoggedUser(responseUser);
    }
}
