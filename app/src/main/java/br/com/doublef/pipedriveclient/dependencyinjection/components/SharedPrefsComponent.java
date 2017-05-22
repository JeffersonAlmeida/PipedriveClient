package br.com.doublef.pipedriveclient.dependencyinjection.components;


import br.com.doublef.pipedriveclient.dependencyinjection.modules.SharedPrefsModule;
import br.com.doublef.pipedriveclient.sharedprefs.UserPrefs;
import dagger.Component;

@Component ( modules = { SharedPrefsModule.class}, dependencies = {ApplicationComponent.class})
public interface SharedPrefsComponent {

    UserPrefs userPrefs();

}
