package br.com.doublef.pipedriveclient;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import javax.inject.Inject;

import br.com.doublef.pipedriveclient.application.App;
import br.com.doublef.pipedriveclient.datamanager.DataManager;
import br.com.doublef.pipedriveclient.di.components.MockAppComponents;
import br.com.doublef.pipedriveclient.feature.home.view.ContactsListActivity;
import br.com.doublef.pipedriveclient.remote.Service;

public class ContactsListActivityTest {

    @Inject
    Service service;

    @Inject
    DataManager dataManager;

    @Rule
    public ActivityTestRule<ContactsListActivity> contactsListActivity =
            new ActivityTestRule<>(ContactsListActivity.class, true, false);

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {

        App app = (App) InstrumentationRegistry
                .getTargetContext()
                .getApplicationContext();

        MockAppComponents components = (MockAppComponents) app.components();
        components.inject(this);

    }

    @After
    public void tearDown() throws Exception {
        dataManager.logout();
        service = null;
        dataManager = null;
        testFolder.delete();
    }

    @Test
    public void showList() throws Exception {

        contactsListActivity.launchActivity(new Intent());
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView));

        Espresso
                .onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.scrollToPosition(TestSupport.SIZE_OF_CONTACTS_LIST-1))
                .perform(RecyclerViewActions.actionOnItemAtPosition(95, ViewActions.click()));

        Thread.sleep(3000);

    }

}