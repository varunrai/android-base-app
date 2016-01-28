// ---------------------------------------------------------------------
// Copyright 2016 Zulu Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
// ---------------------------------------------------------------------

package org.zulu.apps.sgx.runner;

import android.app.Application;
import android.app.KeyguardManager;
import android.os.PowerManager;
import android.support.test.runner.AndroidJUnitRunner;

import static android.content.Context.KEYGUARD_SERVICE;
import static android.content.Context.POWER_SERVICE;
import static android.os.PowerManager.ACQUIRE_CAUSES_WAKEUP;
import static android.os.PowerManager.FULL_WAKE_LOCK;
import static android.os.PowerManager.ON_AFTER_RELEASE;

/**
 * Extension of AndroidJUnitRunner that adds some functionality to unblock the device screen
 * before starting the tests.
 */
public class UnlockDeviceAndroidJUnitRunner extends AndroidJUnitRunner {

    private PowerManager.WakeLock mWakeLock;

    @Override
    public void onStart() {
        Application application = (Application) getTargetContext().getApplicationContext();
        String simpleName = UnlockDeviceAndroidJUnitRunner.class.getSimpleName();
        // Unlock the device so that the tests can input keystrokes.
        ((KeyguardManager) application.getSystemService(KEYGUARD_SERVICE))
                .newKeyguardLock(simpleName)
                .disableKeyguard();
        // Wake up the screen.
        PowerManager powerManager = ((PowerManager) application.getSystemService(POWER_SERVICE));
        mWakeLock = powerManager.newWakeLock(FULL_WAKE_LOCK | ACQUIRE_CAUSES_WAKEUP |
                ON_AFTER_RELEASE, simpleName);
        mWakeLock.acquire();
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mWakeLock.release();
    }
}
