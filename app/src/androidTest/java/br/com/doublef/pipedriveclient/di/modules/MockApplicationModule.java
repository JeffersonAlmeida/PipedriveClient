package br.com.doublef.pipedriveclient.di.modules;

import br.com.doublef.pipedriveclient.MockApp;
import br.com.doublef.pipedriveclient.application.App;
import dagger.Module;
import dagger.Provides;

@Module
public class MockApplicationModule {

    @Provides
    App provideApp(){
        return MockApp.get();
    }



}
