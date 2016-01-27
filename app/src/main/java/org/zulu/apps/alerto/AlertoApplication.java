package org.zulu.apps.alerto;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;

import org.zulu.apps.alerto.injection.component.ApplicationComponent;
import org.zulu.apps.alerto.injection.component.DaggerApplicationComponent;
import org.zulu.apps.alerto.injection.module.ApplicationModule;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

public class AlertoApplication extends Application  {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Fabric.with(this, new Crashlytics());
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
