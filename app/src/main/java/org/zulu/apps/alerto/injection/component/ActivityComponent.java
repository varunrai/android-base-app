package org.zulu.apps.alerto.injection.component;

import dagger.Component;
import org.zulu.apps.alerto.injection.PerActivity;
import org.zulu.apps.alerto.injection.module.ActivityModule;
import org.zulu.apps.alerto.ui.main.MainActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
