package org.zulu.apps.alerto.test.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import org.zulu.apps.alerto.injection.component.ApplicationComponent;
import org.zulu.apps.alerto.test.common.injection.module.ApplicationTestModule;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
