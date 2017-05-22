package br.com.doublef.pipedriveclient.di.components;

import br.com.doublef.pipedriveclient.dependencyinjection.components.RestApiComponent;
import br.com.doublef.pipedriveclient.di.modules.MockRestApiModule;
import br.com.doublef.pipedriveclient.remote.Service;
import dagger.Component;

@Component ( modules = MockRestApiModule.class )
public interface MockRestApiComponent extends RestApiComponent {

    Service service();

}
