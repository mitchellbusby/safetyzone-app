package com.safetyzone.safetyzone;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mitchellbusby on 1/10/2015.
 */
public class ContactsAdapter<String> extends ArrayAdapter<String> {
    public ContactsAdapter(Context context, int resource, int textViewResourceId, ArrayList<String> objects) {
        super(context, resource, textViewResourceId, objects);
    }
    @Override
    public void add(String object) {
        super.add(object);
        //notifyDataSetChanged();
    }

}
