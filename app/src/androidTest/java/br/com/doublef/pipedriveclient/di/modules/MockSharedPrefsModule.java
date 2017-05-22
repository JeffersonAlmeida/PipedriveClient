package br.com.doublef.pipedriveclient.di.modules;


import br.com.doublef.pipedriveclient.MockApp;
import br.com.doublef.pipedriveclient.mocked.MockSharedPrefs;
import br.com.doublef.pipedriveclient.sharedprefs.UserPrefs;
import dagger.Module;
import dagger.Provides;

@Module
public class MockSharedPrefsModule {

    @Provides
    UserPrefs userPrefs(){
        return new MockSharedPrefs(MockApp.get().getApplicationContext());
    }
}
