package com.safetyzone.safetyzone;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by BeckLyons on 13/10/2015.
 */

public class safetytipAdapter<String> extends ArrayAdapter<String> {
    public safetytipAdapter(Context context, int resource, int textViewResourceId, ArrayList<String> objects) {
        super(context, resource, textViewResourceId, objects);
    }
    @Override
    public void add(String object) {

    }
}
