package com.safetyzone.safetyzone;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

/**
 * Created by Zak on 31/10/2015.
 */
public class SafeService extends IntentService {
    final static private long ONE_SECOND = 1000;
    final static private long TWENTY_SECONDS = ONE_SECOND * 20;
    PendingIntent pi;
    BroadcastReceiver br;
    AlarmManager am;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public SafeService() {
    super("my intent ");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        

    }




    /*
    protected override void OnStart(string[] args)
{
     log.Info("Info - Service Started");
    _timer = new Timer(10 * 60 * 1000); // every 10 minutes
    _timer.Elapsed += new System.Timers.ElapsedEventHandler(timer_Elapsed);
    _timer.Start(); // <- important
}
     */


}
