package br.com.doublef.pipedriveclient.di.modules;

import br.com.doublef.pipedriveclient.MockApp;
import br.com.doublef.pipedriveclient.database.DatabaseFacade;
import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

@Module
public class MockDatabaseModule {

    private static final String filename = MockDatabaseModule.class.getSimpleName() + "-test-realm";

    @Provides
    Realm provideRealm() {

        Realm.init(MockApp.get());

        RealmConfiguration testConfig = new RealmConfiguration.Builder()
                .inMemory()
                .name(filename)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm testRealm = Realm.getInstance(testConfig);
        return testRealm;
    }

    @Provides
    DatabaseFacade databaseFacade(Realm realm){
        return new DatabaseFacade(realm);
    }
}
