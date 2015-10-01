package com.safetyzone.safetyzone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Contacts extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        onCreateCustom();
        List<String> values = Arrays.asList("Jess D'Ali", "Mitchell Busby", "Bec Lyons");
        populateContactList(values);

    }
    public void onCreateCustom() {
        final android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setTitle("Blah");
        } catch (NullPointerException e) {

        }
        //toolbar.setTitle("Blah");
    }
    public void addContact(ContactsAdapter<String> contactsAdapter) {
        contactsAdapter.add("New Contact");
    }
    public void addListenerToListModifierButton(final ContactsAdapter<String> contactsAdapter) {
        final Button button = (Button) findViewById(R.id.add_to_list_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContact(contactsAdapter);
            }
        });
    }
    public void populateContactList(List<String> values) {
        final ListView listView = (ListView) findViewById(R.id.contacts_list);
        /*String[] values = new String[] {
                "Blah"
        };*/
        ContactsAdapter<String> arrayAdapter = new ContactsAdapter(getBaseContext(), R.layout.list_row, R.id.list_contact_name,values);
        listView.setAdapter(arrayAdapter);
        addListenerToListModifierButton(arrayAdapter);
    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_contacts, menu);
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

    }*/
}
