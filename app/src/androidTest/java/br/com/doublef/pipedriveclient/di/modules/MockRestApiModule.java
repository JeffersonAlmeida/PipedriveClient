package br.com.doublef.pipedriveclient.di.modules;

import br.com.doublef.pipedriveclient.mocked.MockedService;
import br.com.doublef.pipedriveclient.remote.Service;
import dagger.Module;
import dagger.Provides;

@Module
public class MockRestApiModule {

    @Provides
    Service service(){
        return new MockedService();
    }
}
