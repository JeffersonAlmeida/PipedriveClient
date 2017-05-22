package br.com.doublef.pipedriveclient.database;


import java.util.List;

import br.com.doublef.pipedriveclient.model.Contact;
import br.com.doublef.pipedriveclient.model.PersonData;
import io.realm.Realm;
import io.realm.RealmResults;

public class DatabaseFacade {

    private Realm realm;

    public DatabaseFacade(Realm realm) {
        this.realm = realm;
    }

    public void save(PersonData personData) {
        Realm defaultInstance = Realm.getDefaultInstance();
        defaultInstance.executeTransactionAsync(
                r -> {
                    r.deleteAll();
                    List<Contact> data = personData.getData();
                    r.insertOrUpdate(data);
                });
    }

    public PersonData fetchAll() {
        Realm defaultInstance = Realm.getDefaultInstance();
        RealmResults<Contact> all = defaultInstance.where(Contact.class).findAll();
        PersonData personData = new PersonData();
        personData.setData(all);
        return personData;
    }

    public void clearDatabase(){
        realm.executeTransaction(r -> r.deleteAll());
    }
}
