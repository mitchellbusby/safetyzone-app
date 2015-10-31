package com.safetyzone.safetyzone;

import android.app.Activity;
import android.app.Application;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

//import com.google.android.gms.common.GoogleApiAvailability;

/**
 * Created by Zak on 25/10/2015.
 */
public class LocationService extends Activity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    private static final String TAG_PLAYSERVICE = "PLayer Services";
    public GoogleApiClient googleApiClient;
    public Location lastLocation;
    public static String latitudeText = "test";
    public static String longitudeText= "test2";



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        buildGoogleApiClient();
        googleApiClient.connect();
        super.onCreate(savedInstanceState);
    }


    /*
    getlastknown location
    The precision of the location returned by this call is determined by
    the permission setting you put in your app manifest,
     */

    //To connect to the API, you need to create an instance of the Google Play services API client
    protected synchronized void buildGoogleApiClient() {

        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

    }

    public static String getLongitudeText() {
        return longitudeText;
    }

    public static String getLatitudeText() {
        return latitudeText;
    }


    @Override
    public void onConnected(Bundle bundle) {
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(
                googleApiClient);

        if (lastLocation != null) {
            latitudeText = String.valueOf(lastLocation.getLatitude());
            longitudeText = String.valueOf(lastLocation.getLongitude());
        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG_PLAYSERVICE, "GoogleApiClient connection has been suspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG_PLAYSERVICE, "GoogleApiClient connection has failed");
    }
}
