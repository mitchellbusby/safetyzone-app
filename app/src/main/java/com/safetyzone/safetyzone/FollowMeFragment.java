package com.safetyzone.safetyzone;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BeckLyons on 31/10/2015.
 */
public class FollowMeFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_follow_me, container, false);


        int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getContext());

        if(isAvailable == ConnectionResult.SUCCESS)
        {
            SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
        else if(GooglePlayServicesUtil.isUserRecoverableError(isAvailable))
        {
            //if the connection is is not successful, it checks if that connection result is recoverable.
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable, getActivity(), 937);
            dialog.show();
        }
        else
        {
            //if cant connect for other reasons it will inform the user
            Toast.makeText(getContext(), "Can't connect to Google Play services", Toast.LENGTH_SHORT).show();
        }




        Button button = (Button) view.findViewById(R.id.followOn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SafetyzoneApplication safetyzoneApplication = SafetyzoneApplication.get();
                Toast.makeText(getContext(), "Sending sms...", Toast.LENGTH_LONG).show();
                String defaultMessage1 = "Dear friend, I am traveling on a dangerous road and would like you to " +
                        "ensure my safety through following me. If I do not send you a text in the next hour confirming my arrival" +
                        "at the destination, please know I have reach my wicked end. last known location "+ safetyzoneApplication.getLastlocation().getLatitude() + " " + safetyzoneApplication.getLastlocation().getLongitude() ;
                sendSMSMessage(defaultMessage1);
            }
        });

        Button button2 = (Button) view.findViewById(R.id.followOff);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Sending sms...", Toast.LENGTH_LONG).show();
                String defaultMessage2 = "I SURVIVED, I AM LEGEND";
                sendSMSMessage(defaultMessage2);
            }
        });

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        try {
            SafetyzoneApplication safetyzoneApplication = SafetyzoneApplication.get();

            // Add a marker in Sydney and move the camera
            LatLng you = new LatLng(safetyzoneApplication.getLastlocation().getLatitude(), safetyzoneApplication.getLastlocation().getLongitude());
            mMap.addMarker(new MarkerOptions().position(you).title("Be safe, alert contacts where you are"));
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(safetyzoneApplication.getLastlocation().getLatitude(), safetyzoneApplication.getLastlocation().getLongitude()), 14.0f));
        }
        catch (Exception e)
        {
            LatLng sydney = new LatLng(-34, 151);
            mMap.addMarker(new MarkerOptions().position(sydney).title("SafeZone"));
        }


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

    protected void sendSMSMessage(String defaultMessage) {
        Log.i("Send sms", "it worked");


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
