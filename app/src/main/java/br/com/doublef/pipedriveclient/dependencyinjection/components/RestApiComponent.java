package br.com.doublef.pipedriveclient.dependencyinjection.components;

import br.com.doublef.pipedriveclient.dependencyinjection.modules.RestApiModule;
import br.com.doublef.pipedriveclient.remote.Service;
import dagger.Component;

@Component ( modules = RestApiModule.class, dependencies = {SharedPrefsComponent.class})
public interface RestApiComponent {

    Service service();

}
