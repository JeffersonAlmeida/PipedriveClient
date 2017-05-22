package br.com.doublef.pipedriveclient.database.contactdao;

import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.doublef.pipedriveclient.MockApp;
import br.com.doublef.pipedriveclient.database.DatabaseFacade;
import br.com.doublef.pipedriveclient.model.Contact;
import br.com.doublef.pipedriveclient.model.PersonData;
import io.realm.Realm;
import io.realm.RealmConfiguration;

@RunWith(AndroidJUnit4.class)
public class DatabaseFacadeTest {

    private static final String filename = DatabaseFacadeTest.class.getSimpleName() + "-test-realm";

    private DatabaseFacade databaseFacade;

    private Realm testRealm;

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Before
    public void setUp() throws IOException {

        Realm.init(MockApp.get());

        RealmConfiguration testConfig = new RealmConfiguration.Builder()
                .inMemory()
                .name(filename)
                .deleteRealmIfMigrationNeeded()
                .build();

        testRealm = Realm.getInstance(testConfig);
        databaseFacade = new DatabaseFacade(testRealm);
    }

    @After
    public void after(){
        databaseFacade.clearDatabase();
        testFolder.delete();
        databaseFacade = null;
    }

    @Test
    public void save() throws Exception {

        PersonData personData = new PersonData();
        List<Contact> list = new ArrayList<>();
        Contact contact = new Contact();
        contact.setName("Jefferson");
        list.add(contact);
        personData.setData(list);
        databaseFacade.save(personData);

        PersonData pd = databaseFacade.fetchAll();
        List<Contact> contacts = pd.getData();

        Assert.assertEquals("Jefferson", contacts.get(0).getName());

    }

}