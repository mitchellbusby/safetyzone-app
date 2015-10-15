package com.safetyzone.safetyzone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by mitchellbusby on 2/10/2015.
 */
public class ContactsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        ArrayList<String> values = new ArrayList<>();
        values.add("Jess D'Ali");
        values.add("Mitchell Busby");
        values.add("Bec Lyons");
        populateContactList(view, values);
        return view;
    }

    public void onAddClicked(View view) {
        //startActivity(new Intent(this, AddContactActivity.class));
    }


    public void addContact(ContactsAdapter<String> contactsAdapter) {
        contactsAdapter.add("New Contact");
    }


    public void addListenerToListModifierButton(View view, final ContactsAdapter<String> contactsAdapter) {
        final Button button = (Button) view.findViewById(R.id.add_to_list_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //addContact(contactsAdapter);
                startActivity(new Intent(getContext(), AddContactActivity.class));
            }
        });
    }//cn at l

    public void populateContactList(View view, ArrayList<String> values) {
        final ListView listView = (ListView) view.findViewById(R.id.contacts_list);
            /*String[] values = new String[] {
                    "Blah"
            };*/
        ContactsAdapter<String> arrayAdapter = new ContactsAdapter(view.getContext(), R.layout.list_row, R.id.list_contact_name, values);
        listView.setAdapter(arrayAdapter);
        addListenerToListModifierButton(view, arrayAdapter);
    }


}
