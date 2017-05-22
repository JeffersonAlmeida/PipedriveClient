package br.com.doublef.pipedriveclient.dependencyinjection.modules;

import br.com.doublef.pipedriveclient.application.App;
import br.com.doublef.pipedriveclient.database.DatabaseFacade;
import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

@Module
public class DatabaseModule {

    @Provides
    Realm provideRealm(App application) {
        Realm.init(application.getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        Realm defaultInstance = Realm.getDefaultInstance();
        defaultInstance.isAutoRefresh();
        return defaultInstance;
    }

    @Provides
    DatabaseFacade databaseFacade(Realm realm){
        return  new DatabaseFacade(realm);
    }

}
