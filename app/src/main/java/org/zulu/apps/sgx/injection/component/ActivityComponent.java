package org.zulu.apps.sgx.injection.component;

import dagger.Component;
import org.zulu.apps.sgx.injection.PerActivity;
import org.zulu.apps.sgx.injection.module.ActivityModule;
import org.zulu.apps.sgx.ui.main.MainActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
