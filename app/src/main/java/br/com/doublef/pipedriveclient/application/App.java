package br.com.doublef.pipedriveclient.application;


import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.orhanobut.hawk.Hawk;

import br.com.doublef.pipedriveclient.dependencyinjection.components.AppComponents;
import br.com.doublef.pipedriveclient.dependencyinjection.components.ApplicationComponent;
import br.com.doublef.pipedriveclient.dependencyinjection.components.DaggerAppComponents;
import br.com.doublef.pipedriveclient.dependencyinjection.components.DaggerApplicationComponent;
import br.com.doublef.pipedriveclient.dependencyinjection.components.DaggerDatabaseComponent;
import br.com.doublef.pipedriveclient.dependencyinjection.components.DaggerRestApiComponent;
import br.com.doublef.pipedriveclient.dependencyinjection.components.DaggerSharedPrefsComponent;
import br.com.doublef.pipedriveclient.dependencyinjection.components.DatabaseComponent;
import br.com.doublef.pipedriveclient.dependencyinjection.components.RestApiComponent;
import br.com.doublef.pipedriveclient.dependencyinjection.components.SharedPrefsComponent;
import io.fabric.sdk.android.Fabric;

public class App extends Application {

    private AppComponents appComponents;

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        instance = this;
        initDependencyInjectionComponents();
        initSharedPreferences();
    }

    private void initSharedPreferences() {
        Hawk.init(this).build();
    }

    private void initDependencyInjectionComponents() {

        ApplicationComponent applicationComponent = DaggerApplicationComponent
                .builder()
                .build();

        DatabaseComponent databaseComponent = DaggerDatabaseComponent
                .builder()
                .applicationComponent(applicationComponent)
                .build();

        SharedPrefsComponent sharedPrefsComponent = DaggerSharedPrefsComponent
                .builder()
                .applicationComponent(applicationComponent)
                .build();

        RestApiComponent restApiComponent = DaggerRestApiComponent
                .builder()
                .sharedPrefsComponent(sharedPrefsComponent)
                .build();

        appComponents = DaggerAppComponents
                .builder()
                .applicationComponent(applicationComponent)
                .databaseComponent(databaseComponent)
                .restApiComponent(restApiComponent)
                .sharedPrefsComponent(sharedPrefsComponent)
                .build();

    }

    public AppComponents components() {
        return appComponents;
    }

    public static synchronized App get() {
        if (instance == null) {
            final String className = App.class.getSimpleName();
            throw new IllegalStateException(className + " is not initialized!");
        }
        return instance;
    }


}
