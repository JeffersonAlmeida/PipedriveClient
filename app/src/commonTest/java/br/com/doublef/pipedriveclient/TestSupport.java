package br.com.doublef.pipedriveclient;


import java.util.ArrayList;
import java.util.List;

import br.com.doublef.pipedriveclient.model.Contact;
import br.com.doublef.pipedriveclient.model.PersonData;
import rx.Observable;

public class TestSupport {

    public static String DEFAULT_NAME_FOR_TEST = "Maria da Silva";
    public static int SIZE_OF_CONTACTS_LIST = 100;

    public Observable<PersonData> createPersonData(){

        PersonData personData = new PersonData();
        List<Contact> contactList = new ArrayList<>();

        Contact contact = new Contact();
        contact.setId(1);
        contact.setName(DEFAULT_NAME_FOR_TEST);
        contactList.add(contact);
        personData.setData(contactList);

        return Observable.just(personData);
    }

    public Observable<PersonData> createBiggerPersonData(){

        PersonData personData = new PersonData();
        List<Contact> contactList = new ArrayList<>();

        for (int i = 0; i < SIZE_OF_CONTACTS_LIST; i++ ) {
            Contact contact = new Contact();
            contact.setId(i);
            contact.setName(String.valueOf(i));
            contactList.add(contact);
        }

        personData.setData(contactList);

        return Observable.just(personData);
    }
}
