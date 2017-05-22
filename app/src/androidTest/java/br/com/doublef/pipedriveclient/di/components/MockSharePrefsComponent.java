package br.com.doublef.pipedriveclient.di.components;

import br.com.doublef.pipedriveclient.dependencyinjection.components.SharedPrefsComponent;
import br.com.doublef.pipedriveclient.di.modules.MockSharedPrefsModule;
import br.com.doublef.pipedriveclient.sharedprefs.UserPrefs;
import dagger.Component;

@Component ( modules = MockSharedPrefsModule.class, dependencies = MockApplicationComponent.class )
public interface MockSharePrefsComponent extends SharedPrefsComponent {

    UserPrefs userPrefs();

}
