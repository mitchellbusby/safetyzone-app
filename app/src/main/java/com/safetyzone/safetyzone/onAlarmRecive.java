package com.safetyzone.safetyzone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Zak on 31/10/2015.
 */
public class onAlarmRecive extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("test", "BroadcastReceiver, in onReceive:");

        // Start the general activty
        //Intent i = new Intent(context, GeneralActivity.class);
        //i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //context.startActivity(i);

        //every 10 min check time, battery and lga


    }
}
