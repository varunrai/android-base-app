package org.zulu.apps.alerto.injection.component;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;
import org.zulu.apps.alerto.data.DataManager;
import org.zulu.apps.alerto.data.SyncService;
import org.zulu.apps.alerto.data.local.DatabaseHelper;
import org.zulu.apps.alerto.data.local.PreferencesHelper;
import org.zulu.apps.alerto.data.remote.RibotsService;
import org.zulu.apps.alerto.injection.ApplicationContext;
import org.zulu.apps.alerto.injection.module.ApplicationModule;

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
