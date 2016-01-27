package org.zulu.apps.sgx;

import android.app.Application;
import android.content.Context;

import org.zulu.apps.sgx.injection.component.ApplicationComponent;
import org.zulu.apps.sgx.injection.component.DaggerApplicationComponent;
import org.zulu.apps.sgx.injection.module.ApplicationModule;

import timber.log.Timber;

//import com.crashlytics.android.Crashlytics;
//import io.fabric.sdk.android.Fabric;

public class AlertoApplication extends Application  {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
           // Fabric.with(this, new Crashlytics());
        }
    }

    public static AlertoApplication get(Context context) {
        return (AlertoApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
