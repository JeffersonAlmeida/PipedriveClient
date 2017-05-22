package br.com.doublef.pipedriveclient.di.components;

import br.com.doublef.pipedriveclient.application.App;
import br.com.doublef.pipedriveclient.dependencyinjection.components.ApplicationComponent;
import br.com.doublef.pipedriveclient.di.modules.MockApplicationModule;
import dagger.Component;

@Component( modules = MockApplicationModule.class )
public interface MockApplicationComponent extends ApplicationComponent {

    App app();

}
