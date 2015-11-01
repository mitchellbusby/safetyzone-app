package com.safetyzone.safetyzone;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mitchellbusby on 2/10/2015.
 */
public class ContactsFragment extends Fragment {

    private ListView mContactListView;
    ContactsAdapter contactAdapter;
    public View view;
    public static final String TAG = "ContactsFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        setupList(view);

        this.view = view;
        return view;
    }


    public View getView(){
        return view;
    }

    public void setupList(View view) {
        List<ContactData> contactDataList = ContactDatabaseHelper.get(getContext()).getContactDataList(null);
        //contactDataList.get(0).isDesignated();

        TextView designatedContact = (TextView) view.findViewById(R.id.designated_contact);

        for (int i=0; i<contactDataList.size(); i++) {
            if (contactDataList.get(i).isDesignated()==1) {
                designatedContact.setText(" "+ contactDataList.get(i).getmName());
            }
        }

        mContactListView = (ListView) view.findViewById(R.id.contacts_list);
        //gets the cursor
        Log.d("setup", "list set up");
        //ContactCursor
        ContactDatabaseHelper.ContactCursor cursor = null;
        cursor = ContactDatabaseHelper.get(getActivity()).getContactCursor(null);

        Button button = (Button) view.findViewById(R.id.add_to_list_button);



        if (contactDataList.size() < SafetyzoneApplication.getContactlimit()) {
            button.setEnabled(true);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getContext(), AddContactActivity.class));
                }
            });
        } else {
            button.setEnabled(false);
        }

        contactAdapter = new ContactsAdapter(view.getContext(), cursor);

        mContactListView.setAdapter(contactAdapter);

        contactAdapter.notifyDataSetChanged();
    }



    public void populateContactList(View view, ArrayList<String> values) {
        final ListView listView = (ListView) view.findViewById(R.id.contacts_list);

            /*String[] values = new String[] {
                    "Blah"
            };*/
        //ContactsAdapter<String> arrayAdapter = new ContactsAdapter(view.getContext(), R.layout.list_row, R.id.list_contact_name, values);
        //listView.setAdapter(arrayAdapter);
        //addListenerToListModifierButton(view, arrayAdapter);
    }


    @Override
    public void onResume() {
        super.onResume();
        contactAdapter.notifyDataSetChanged();
        setupList(getView());

        //getSupportLoaderManager().getLoader(0).onContentChanged();
        //contactAdapter.notifyDataSetChanged();

    }


    //public void addContact(ContactsAdapter<String> contactsAdapter) {
    //contactsAdapter.add("New Contact");
    // }

    //read http request from azure
    /*
    TODO: Volley, setup unbound service which fires requests which will 2 min request data from the
     azure
     reqeust.method.get
     use the url
     need the coordinates
     azure web api,

     util . serilise = the util folder can hold the functionality of the servic.
     */




    //TODO
    private class ContactsAdapter extends CursorAdapter {


        public ContactsAdapter(Context context, ContactDatabaseHelper.ContactCursor cursor) {
            super(context, cursor);
        }


        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_data, null);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            // This is made "final" so that it can be referenced from within the anonymous OnClickListener below. TODO remove final
            final ContactData contactData = ((ContactDatabaseHelper.ContactCursor) cursor).getContactData();

            TextView contactNameTV = (TextView) view.findViewById(R.id.list_item_contact_name_textview);
            TextView contactNumberTv = (TextView) view.findViewById(R.id.list_item_contact_number_textview);
            TextView friendSinceDateTextView = (TextView) view.findViewById(R.id.list_item_contact_since_date_textview);
            TextView checked = (TextView) view.findViewById(R.id.checkedd);

            if (contactData.isDesignated()!=1) {
                checked.setVisibility(view.GONE);
            }
            else {
                checked.setText("Designated Contact");
            }

            contactNameTV.setText(contactData.getmName());
            contactNumberTv.setText(contactData.getmNumber());
            friendSinceDateTextView.setText(new SimpleDateFormat("dd/mm/yyyy").format(contactData.getmContactSince()));

            ImageButton edit = (ImageButton) view.findViewById(R.id.edit_button);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent singleContact = new Intent(getContext(), ContactSingleActivity.class);
                    singleContact.putExtra("name",contactData.getmName());
                    singleContact.putExtra("id", contactData.getmId());
                    singleContact.putExtra("deso", contactData.isDesignated());
                    singleContact.putExtra("number", contactData.getmNumber());
                    startActivity(singleContact);
                }


            });

            ImageButton deleteButton = (ImageButton) view.findViewById(R.id.delete_button);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ContactDatabaseHelper.get(getActivity()).removeContact(contactData);
                    changeCursor(ContactDatabaseHelper.get(getActivity()).getContactCursor(null));
                    SafetyzoneApplication.setAddnew(true);
                    //ContactsFragment frag = getParentFragment();

                    //Button button2 = (Button) view.findViewById(R.id.add_to_list_button);
                    //button2.setEnabled(true); TODO // FIXME: 31/10/2015
                }
            });

        }

    }
}
