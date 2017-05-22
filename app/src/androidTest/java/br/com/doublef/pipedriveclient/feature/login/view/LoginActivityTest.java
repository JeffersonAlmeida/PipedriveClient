package br.com.doublef.pipedriveclient.feature.login.view;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.inject.Inject;

import br.com.doublef.pipedriveclient.MockApp;
import br.com.doublef.pipedriveclient.datamanager.DataManager;
import br.com.doublef.pipedriveclient.di.components.MockAppComponents;

public class LoginActivityTest {

    @Inject
    DataManager dataManager;

    @Rule
    public ActivityTestRule<LoginActivity> login =
            new ActivityTestRule<>(LoginActivity.class, true, false);

    @Before
    public void setUp() throws Exception {

        MockApp app = (MockApp) InstrumentationRegistry
                .getTargetContext()
                .getApplicationContext();

        MockAppComponents components = (MockAppComponents) app.components();
        components.inject(this);

    }

    @After
    public void tearDown() throws Exception {
        dataManager.logout();
    }

    @Test
    public void login() throws Exception {
        login.launchActivity(new Intent());
    }

}