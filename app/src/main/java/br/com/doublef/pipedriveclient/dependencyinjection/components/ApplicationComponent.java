package br.com.doublef.pipedriveclient.dependencyinjection.components;

import br.com.doublef.pipedriveclient.application.App;
import br.com.doublef.pipedriveclient.dependencyinjection.modules.ApplicationModule;
import dagger.Component;

@Component(modules = ApplicationModule.class )
public interface ApplicationComponent {

    App app();

}
