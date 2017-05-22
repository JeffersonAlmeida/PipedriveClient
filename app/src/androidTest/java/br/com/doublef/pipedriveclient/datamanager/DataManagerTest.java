package br.com.doublef.pipedriveclient.datamanager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;

import java.util.List;

import br.com.doublef.pipedriveclient.MockApp;
import br.com.doublef.pipedriveclient.TestSupport;
import br.com.doublef.pipedriveclient.database.DatabaseFacade;
import br.com.doublef.pipedriveclient.model.PersonData;
import br.com.doublef.pipedriveclient.remote.Service;
import br.com.doublef.pipedriveclient.sharedprefs.SharedPrefs;
import br.com.doublef.pipedriveclient.util.RxSchedulersOverrideRule;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import rx.Observable;
import rx.observers.TestSubscriber;

public class DataManagerTest {

    private static final String filename = DataManagerTest.class.getSimpleName() + "-test-realm";

    private Service service;

    private DataManager dataManager;

    private Realm testRealm;

    @Rule
    public RxSchedulersOverrideRule rule = new RxSchedulersOverrideRule();

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        service = Mockito.mock(Service.class);

        Realm.init(MockApp.get());

        RealmConfiguration testConfig = new RealmConfiguration.Builder()
                .inMemory()
                .name(filename)
                .deleteRealmIfMigrationNeeded()
                .build();

        testRealm = Realm.getInstance(testConfig);

        DatabaseFacade databaseFacade = new DatabaseFacade(testRealm);
        SharedPrefs userPrefs = new SharedPrefs(MockApp.get().getApplicationContext());
        dataManager = new DataManager(service, databaseFacade, userPrefs);

    }

    @After
    public void tearDown() throws Exception {
        dataManager.logout();
        testFolder.delete();
        dataManager = null;
    }

    @Test
    public void test(){

        Observable<PersonData> personData = new TestSupport().createPersonData();
        Mockito.when(service.fetchDataFromRemote()).thenReturn(personData);

        Observable<PersonData> personDataObservable = dataManager.fetchData();
        TestSubscriber<PersonData> testSubscriber = TestSubscriber.create();
        personDataObservable.subscribe(testSubscriber);

        testSubscriber.assertNoErrors();

        List<PersonData> onNextEvents = testSubscriber.getOnNextEvents();
        PersonData data = onNextEvents.get(0);
        String name = data.getData().get(0).getName();
        Assert.assertEquals(TestSupport.DEFAULT_NAME_FOR_TEST, name);

    }

}