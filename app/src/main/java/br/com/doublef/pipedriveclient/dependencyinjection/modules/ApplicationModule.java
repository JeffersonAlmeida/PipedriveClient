package br.com.doublef.pipedriveclient.dependencyinjection.modules;


import br.com.doublef.pipedriveclient.application.App;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Provides
    App provideApp(){
        return App.get();
    }
}
