package br.com.doublef.pipedriveclient.di.components;

import br.com.doublef.pipedriveclient.database.DatabaseFacade;
import br.com.doublef.pipedriveclient.dependencyinjection.components.DatabaseComponent;
import br.com.doublef.pipedriveclient.di.modules.MockDatabaseModule;
import dagger.Component;

@Component ( modules = { MockDatabaseModule.class} )
public interface MockDatabaseComponent extends DatabaseComponent {
    DatabaseFacade databaseFacade();
}
