package br.com.doublef.pipedriveclient.feature.home.contract;

import br.com.doublef.pipedriveclient.base.MvpView;
import br.com.doublef.pipedriveclient.model.PersonData;

public interface HomeContract {

    interface HomeView extends MvpView {

        void showList(PersonData contactList);

        void showError(String error);

        void showProgressBar();
        void hideProgressBar();

    }

    interface HomePresenter {

        void loadData();
        void loadLocalData();
        void logout();

    }
}
