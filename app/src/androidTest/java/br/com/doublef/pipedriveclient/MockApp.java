package br.com.doublef.pipedriveclient;

import br.com.doublef.pipedriveclient.application.App;
import br.com.doublef.pipedriveclient.dependencyinjection.components.AppComponents;
import br.com.doublef.pipedriveclient.di.components.DaggerMockAppComponents;
import br.com.doublef.pipedriveclient.di.components.DaggerMockApplicationComponent;
import br.com.doublef.pipedriveclient.di.components.DaggerMockDatabaseComponent;
import br.com.doublef.pipedriveclient.di.components.DaggerMockRestApiComponent;
import br.com.doublef.pipedriveclient.di.components.DaggerMockSharePrefsComponent;
import br.com.doublef.pipedriveclient.di.components.MockAppComponents;
import br.com.doublef.pipedriveclient.di.components.MockApplicationComponent;
import br.com.doublef.pipedriveclient.di.components.MockDatabaseComponent;
import br.com.doublef.pipedriveclient.di.components.MockRestApiComponent;
import br.com.doublef.pipedriveclient.di.components.MockSharePrefsComponent;

public class MockApp extends App {

    private static MockApp instance;

    private MockAppComponents mockAppComponents;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initDependencyInjectionComponents();
    }

    private void initDependencyInjectionComponents() {

        MockRestApiComponent mockRestApiComponent = DaggerMockRestApiComponent
                .builder()
                .build();

        MockApplicationComponent mockApplicationComponent = DaggerMockApplicationComponent
                .builder()
                .build();

        MockDatabaseComponent mockDatabaseComponent = DaggerMockDatabaseComponent.builder().build();

        MockSharePrefsComponent mockSharePrefsComponent = DaggerMockSharePrefsComponent
                .builder()
                .mockApplicationComponent(mockApplicationComponent)
                .build();

        mockAppComponents = DaggerMockAppComponents
                .builder()
                .mockApplicationComponent(mockApplicationComponent)
                .mockRestApiComponent(mockRestApiComponent)
                .mockDatabaseComponent(mockDatabaseComponent)
                .mockSharePrefsComponent(mockSharePrefsComponent)
                .build();
    }

    @Override
    public AppComponents components() {
        return mockAppComponents;
    }

    public static synchronized MockApp get() {
        if (instance == null) {
            final String className = App.class.getSimpleName();
            throw new IllegalStateException(className + " is not initialized!");
        }
        return instance;
    }
}