package br.com.doublef.pipedriveclient.base;

public interface MvpPresenter <T extends MvpView> {

    void attachView(T view);
    void detachView();

}
