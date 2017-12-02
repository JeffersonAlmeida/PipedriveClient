package br.com.doublef.pipedriveclient.feature.home.contract;

import java.util.List;

import br.com.doublef.pipedriveclient.base.MvpView;
import br.com.doublef.pipedriveclient.model.Post;

public interface HomeContract {

    interface HomeView extends MvpView {

        void showList(List<Post> contactList);

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
