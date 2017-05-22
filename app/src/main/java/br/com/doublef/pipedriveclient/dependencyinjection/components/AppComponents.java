package br.com.doublef.pipedriveclient.dependencyinjection.components;


import javax.inject.Singleton;

import br.com.doublef.pipedriveclient.feature.details.DetailsActivity;
import br.com.doublef.pipedriveclient.feature.home.view.ContactsListActivity;
import br.com.doublef.pipedriveclient.feature.login.view.LoginActivity;
import dagger.Component;


@Singleton
@Component ( dependencies = {
        RestApiComponent.class,
        DatabaseComponent.class,
        ApplicationComponent.class,
        SharedPrefsComponent.class
})
public interface AppComponents {

    void inject(ContactsListActivity contactsListActivity);
    void inject(LoginActivity loginActivity);
    void inject(DetailsActivity detailsActivity);

}
