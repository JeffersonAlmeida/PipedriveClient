package br.com.doublef.pipedriveclient.dependencyinjection.components;

import br.com.doublef.pipedriveclient.database.DatabaseFacade;
import br.com.doublef.pipedriveclient.dependencyinjection.modules.DatabaseModule;
import dagger.Component;

@Component( modules = DatabaseModule.class, dependencies = ApplicationComponent.class )
public interface DatabaseComponent {

    DatabaseFacade databaseFacade();

}
