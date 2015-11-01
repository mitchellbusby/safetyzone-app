package com.safetyzone.safetyzone;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Zak on 7/10/2015.
 */
public class AddContactActivity extends ActionBarActivity {

    int checked=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact_data);
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

                List<ContactData> contactDataList = ContactDatabaseHelper.get(this).getContactDataList(null);
                if (checked==1) {


                    for (int i = 0; i < contactDataList.size(); i++) {
                        if (contactDataList.get(i).isDesignated()==1) {
                            contactDataList.get(i).resetDesignated();
                        }
                    }
                }
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
}
