package com.safetyzone.safetyzone;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by Zak on 7/10/2015.
 */
public class AddContactActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact_data);
    }

    /**
     * Callback for the cancel button which takes the user back to the main activity.
     *
     */
    public void onCancelClicked(View view) {
        //ends activity
        finish();
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

            ContactData contactData = new ContactData(0, name.getText().toString(), Integer.parseInt(number.getText().toString()), dateInMillisecond);
            ContactDatabaseHelper.get(this).addContact(contactData);
        }
        catch(Exception ex)
        {
            Log.e("AddFriendActivity", "onAddClicked Exception: ", ex);
        }

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_add_contact_data, menu); didnt make
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
