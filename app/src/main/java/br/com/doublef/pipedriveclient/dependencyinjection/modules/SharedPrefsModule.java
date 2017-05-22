package br.com.doublef.pipedriveclient.dependencyinjection.modules;

import br.com.doublef.pipedriveclient.application.App;
import br.com.doublef.pipedriveclient.sharedprefs.SharedPrefs;
import br.com.doublef.pipedriveclient.sharedprefs.UserPrefs;
import dagger.Module;
import dagger.Provides;

@Module
public class SharedPrefsModule {

    @Provides
    UserPrefs sharedPrefs(App app){
        return new SharedPrefs(app.getApplicationContext());
    }

}
