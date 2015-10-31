package com.safetyzone.safetyzone;

/**
 * Created by BeckLyons on 1/11/2015.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by BeckLyons on 1/11/2015.
 */
public class ContactSingleActivity extends ActionBarActivity {

    int checked=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_contact_activity);

        popup();
    }

    /**
     * Callback for the cancel button which takes the user back to the main activity.
     */
    public void onCancelClicked(View view) {
        //ends activity
        finish();
    }

    /*button for assigning contact */
    public void onAssigned(View view) {
        if (checked==1) {
            checked = 0;
        } else {
            checked=1;
        }
    }

    /**
     * Callback for the add button which adds a new bus data row to the database and returns
     * the user back to the main activity.
     */
    public void onAddClicked(View view) {

        try {

            long dateInMillisecond = Calendar.getInstance().getTimeInMillis();

            EditText number = (EditText) findViewById(R.id.add_contact_number_edittext);
            EditText name = (EditText) findViewById(R.id.add_contact_name_edittext);
            //CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox_assigned);
            //
            //String numberPadded = "";
            //int realNumber = 0;

            try {
                //numberPadded = number.getText().toString();
                //realNumber = Integer.parseInt(numberPadded); // this wont work...

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Other symbols present in contact number!", Toast.LENGTH_LONG).show();
            }


            if (number.getText().toString().length() == 10) {
                //check checkbox
                ContactData contactData = new ContactData(0, name.getText().toString(), number.getText().toString(), dateInMillisecond, checked);
                ContactDatabaseHelper.get(this).addContact(contactData);
                finish();

            } else {
                Toast.makeText(getApplicationContext(), "Invalid number! Please enter a 10 digit number" , Toast.LENGTH_LONG).show();
                //number.setText("");
            }

        } catch (Exception ex) {

            Log.e("AddContactActivity", "onAddClicked Exception: ", ex);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contacts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void popup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Set Alert Dialog Title
        builder.setTitle("New Designated Contact");

        // Set an Icon for this Alert Dialog
        //builder.setIcon(R.drawable.icon1);

        // Set Alert Dialog Message
        builder.setMessage("Are you sure you want to add this contact as a designated contact? Any previously selected designted contacts will be removed.")

                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg0) {

                        checked=1;
                    }
                })

                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg0) {

                        dialog.cancel();
                    }
                })
        ;

        // Create the Alert Dialog
        AlertDialog alertdialog = builder.create();

        // Show Alert Dialog
        alertdialog.show();
    }
}