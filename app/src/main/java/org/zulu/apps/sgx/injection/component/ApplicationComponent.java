package org.zulu.apps.sgx.injection.component;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;

import org.zulu.apps.sgx.data.DataManager;
import org.zulu.apps.sgx.data.SyncService;
import org.zulu.apps.sgx.data.local.DatabaseHelper;
import org.zulu.apps.sgx.data.local.PreferencesHelper;
import org.zulu.apps.sgx.data.remote.RibotsService;
import org.zulu.apps.sgx.injection.ApplicationContext;
import org.zulu.apps.sgx.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext Context context();
    Application application();
    RibotsService ribotsService();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    DataManager dataManager();
    Bus eventBus();

}
