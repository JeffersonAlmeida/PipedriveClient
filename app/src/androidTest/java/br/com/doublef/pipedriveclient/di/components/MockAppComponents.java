package br.com.doublef.pipedriveclient.di.components;

import javax.inject.Singleton;

import br.com.doublef.pipedriveclient.ContactsListActivityTest;
import br.com.doublef.pipedriveclient.dependencyinjection.components.AppComponents;
import br.com.doublef.pipedriveclient.feature.login.view.LoginActivityTest;
import dagger.Component;

@Singleton
@Component (
        dependencies = {
                MockRestApiComponent.class,
                MockDatabaseComponent.class,
                MockApplicationComponent.class,
                MockSharePrefsComponent.class
        })
public interface MockAppComponents extends AppComponents {

    void inject(ContactsListActivityTest contactsListActivityTest);
    void inject(LoginActivityTest loginActivityTest);

}
