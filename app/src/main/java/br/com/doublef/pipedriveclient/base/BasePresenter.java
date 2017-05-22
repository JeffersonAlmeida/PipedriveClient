package br.com.doublef.pipedriveclient.base;

public class BasePresenter<T extends MvpView> implements MvpPresenter<T> {

    T view;

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    public T getView() {
        return view;
    }
}
