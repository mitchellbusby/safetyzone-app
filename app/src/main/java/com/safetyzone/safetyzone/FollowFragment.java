package com.safetyzone.safetyzone;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by mitchellbusby on 3/10/2015.
 */
public class FollowFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    final static private long ONE_SECOND = 1000;
    final static private long TWENTY_SECONDS = ONE_SECOND * 20;
    PendingIntent pi;
    BroadcastReceiver br;
    AlarmManager am;

    public LocationRequest locationRequest;
    public GoogleApiClient googleApiClient;
    public LocationManager locationManager;

    String defaultMessage;

    //LocationClient mLocationClient;

    public Location lastLocation;
    public static String latitudeText = "test3";
    public static String longitudeText = "test2";
    public View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_follow, container, false);
        this.view = view;

        buildGoogleApiClient();
        googleApiClient.connect();

        //getLastKnownLocation();
        locationRequest();
        onConnected(savedInstanceState);


        TextView longditudeTV = (TextView) view.findViewById(R.id.latitudeTextView);
        TextView latitudeTv = (TextView) view.findViewById(R.id.longitudeTextView);
        latitudeTv.setText(latitudeText);
        longditudeTV.setText(longitudeText);

        Button button = (Button) view.findViewById(R.id.panicButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Sending sms...", Toast.LENGTH_LONG).show();
                sendSMSMessage();
            }
        });


        try {
            if (getLocationMode(getContext()) != 3) {
                popup();
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();        }

        //start serive
        AlarmManager alarmManager = (AlarmManager)getContext().getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(getContext(), SafeService.class);
        PendingIntent pending = PendingIntent.getService(getContext(), 0, alarmIntent, 0);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 10 * 1000, pending);


        return view;
    }

    private void getSystemService(String alarmService) {
    }

    public int getLocationMode(Context context) throws Settings.SettingNotFoundException {
        return Settings.Secure.getInt(getActivity().getContentResolver(), Settings.Secure.LOCATION_MODE); //// FIXME: 31/10/2015
        // if lastLocation == null
    }

    protected void startLocationUpdates() {
        //LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, LocationListener.onLocationChanged() );
    }

    protected void locationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    }


    protected synchronized void buildGoogleApiClient() {

        googleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();


    }

    private Location getLastKnownLocation() {
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            Location l = locationManager.getLastKnownLocation(provider);

            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                bestLocation = l;
            }
        }
        if (bestLocation == null) {
            return null;
        }
        return bestLocation;
    }

    @Override
    public void onConnected(Bundle bundle) {
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(
                googleApiClient);

        if (lastLocation != null) {
            latitudeText = String.valueOf(lastLocation.getLatitude());
            longitudeText = String.valueOf(lastLocation.getLongitude());

            TextView longditudeTV = (TextView) view.findViewById(R.id.latitudeTextView);
            TextView latitudeTv = (TextView) view.findViewById(R.id.longitudeTextView);
            latitudeTv.setText(latitudeText);
            longditudeTV.setText(longitudeText);


        } else {
            latitudeText = "33.8650";
            longitudeText = "151.2094";
            locationRequest();
            startLocationUpdates();

        }

        /*
        TextView longditudeTV = (TextView) view.findViewById(R.id.latitudeTextView);
        TextView latitudeTv = (TextView) view.findViewById(R.id.longitudeTextView);
        latitudeTv.setText(latitudeText);
        longditudeTV.setText(longitudeText); */
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(getContext(), "fail2!", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(getContext(), "fail!", Toast.LENGTH_LONG).show();

    }

    public void popup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        // Set Alert Dialog Title
        builder.setTitle("Your location is not enabled");

        // Set an Icon for this Alert Dialog
        //builder.setIcon(R.drawable.icon1);

        // Set Alert Dialog Message
        builder.setMessage("Do you want to turn on location services?")

                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg0) {

                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })

                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg0) {
                        Toast.makeText(getContext(), "you are not safe...", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                })
        ;

        // Create the Alert Dialog
        AlertDialog alertdialog = builder.create();

        // Show Alert Dialog
        alertdialog.show();
    }

    protected void sendSMSMessage() {
        Log.i("Send sms", "it worked");
        defaultMessage = "Hello friend, I am dire need of help - alert Gandalf to send he Eagles! my location is " + latitudeText + " " + longitudeText;

        List<ContactData> contactDataList = ContactDatabaseHelper.get(getContext()).getContactDataList(null);
        ArrayList<String> numbers = new ArrayList<>();
        for (ContactData contactData : contactDataList) {
            numbers.add(contactData.getmNumber());
        }

        try {
            for (String number : numbers) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(number, null, defaultMessage, null, null);
                Toast.makeText(getContext(), "Sms sent!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "sms did not send", Toast.LENGTH_LONG).show();
        }
    }






}
