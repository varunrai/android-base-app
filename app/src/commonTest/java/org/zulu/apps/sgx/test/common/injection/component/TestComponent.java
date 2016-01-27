package org.zulu.apps.sgx.test.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import org.zulu.apps.sgx.injection.component.ApplicationComponent;
import org.zulu.apps.sgx.test.common.injection.module.ApplicationTestModule;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
