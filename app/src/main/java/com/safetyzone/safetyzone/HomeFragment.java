package com.safetyzone.safetyzone;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import java.util.List;

/**
 * Created by BeckLyons on 31/10/2015.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    public View view;
    Button startButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        this.view = view;
        startButton = (Button) view.findViewById(R.id.button2);

        startButton.setOnClickListener(this);

        Button button = (Button) view.findViewById(R.id.panicButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Sending sms...", Toast.LENGTH_LONG).show();
                sendSMSMessage();
            }
        });

        return view;
    }
    public static final HomeFragment newInstance() {
        HomeFragment f = new HomeFragment();
        return f;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        if (v == startButton) {
            Log.e("inside method", "fragment load");
            Fragment fragment2 = new FollowFragment();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.fragment_placeholder, fragment2);
            transaction.commit();

            Log.e("outside method", "fragment loaded...............");
        }
    }

    protected void sendSMSMessage() {
        Log.i("Send sms", "it worked");
        String defaultMessage = "Panic at the disco";

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
