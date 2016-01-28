package org.zulu.apps.sgx.data;

import android.os.Bundle;

import co.mobiwise.fastgcm.GCMListenerService;

/**
 * Created by varun on 28/1/16.
 */
public class CloudMessageService extends GCMListenerService {

    @Override
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);

        //Here is called even app is not running.
        //create your notification here.
    }
}