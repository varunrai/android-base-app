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

package org.zulu.apps.sgx.util;

import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicInteger;

import timber.log.Timber;

/**
 * Espresso Idling resource that handles waiting for RxJava Observables executions.
 * This class must be used with RxIdlingExecutionHook.
 * Before registering this idling resource you must:
 * 1. Create an instance of RxIdlingExecutionHook by passing an instance of this class.
 * 2. Register RxIdlingExecutionHook with the RxJavaPlugins using registerObservableExecutionHook()
 * 3. Register this idle resource with Espresso using Espresso.registerIdlingResources()
 */
public class RxIdlingResource implements IdlingResource {

    private final AtomicInteger mActiveSubscriptionsCount = new AtomicInteger(0);
    private ResourceCallback mResourceCallback;

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public boolean isIdleNow() {
        return mActiveSubscriptionsCount.get() == 0;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mResourceCallback = callback;
    }

    public void incrementActiveSubscriptionsCount() {
        int count = mActiveSubscriptionsCount.incrementAndGet();
        Timber.i("Active subscriptions count increased to %d", count);
    }

    public void decrementActiveSubscriptionsCount() {
        int count = mActiveSubscriptionsCount.decrementAndGet();
        Timber.i("Active subscriptions count decreased to %d", count);
        if (isIdleNow()) {
            Timber.i("There is no active subscriptions, transitioning to Idle");
            mResourceCallback.onTransitionToIdle();
        }
    }

}
