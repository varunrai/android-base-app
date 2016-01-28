package org.zulu.apps.sgx.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.zulu.apps.sgx.AlertoApplication;
import org.zulu.apps.sgx.injection.component.ActivityComponent;
import org.zulu.apps.sgx.injection.component.DaggerActivityComponent;
import org.zulu.apps.sgx.injection.module.ActivityModule;

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(AlertoApplication.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }

}