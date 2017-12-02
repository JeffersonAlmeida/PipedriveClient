package br.com.doublef.pipedriveclient.feature.home.presenter;

import java.util.List;

import javax.inject.Inject;

import br.com.doublef.pipedriveclient.base.BasePresenter;
import br.com.doublef.pipedriveclient.datamanager.DataManager;
import br.com.doublef.pipedriveclient.feature.home.contract.HomeContract;
import br.com.doublef.pipedriveclient.model.Post;
import br.com.doublef.pipedriveclient.remote.RetrofitException;
import br.com.doublef.pipedriveclient.remote.RxUnsubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomePresenterImpl
        extends BasePresenter<HomeContract.HomeView> implements HomeContract.HomePresenter {

    private final DataManager dataManager;

    private Subscription subscription;

    @Inject
    public HomePresenterImpl(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void loadData() {
        doBeforeSubscribe();
        RxUnsubscriber.unsubscribe(subscription);
        subscription = dataManager.fetchUserPostsData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onFetchDataSuccess, this::onFetchDataError);
    }

    @Override
    public void loadLocalData() {
//        dataManager.fetchLocalData()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(this::onFetchDataSuccess, this::onFetchDataError);
    }

    @Override
    public void detachView() {
        super.detachView();
        RxUnsubscriber.unsubscribe(subscription);
    }

    private void doBeforeSubscribe() {
        getView().showProgressBar();
    }

    private void onFetchDataError(Throwable e) {
        RetrofitException exception = (RetrofitException) e;
        getView().showError(exception.toString());
        getView().hideProgressBar();
    }

    private void onFetchDataSuccess(List<Post> data) {
        if ( data != null && !data.isEmpty() ) {
            getView().showList(data);
        }
        getView().hideProgressBar();
    }

    @Override
    public void logout() {
        dataManager.logout();
    }
}
