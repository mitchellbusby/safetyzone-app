package com.safetyzone.safetyzone;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by BeckLyons on 13/10/2015.
 */


public class GeneralAdapter <String> extends ArrayAdapter<String> {

    private final Context context;
    List<String> listArray;




    public GeneralAdapter(Context baseContext, int list_style_navigation, int list_navigation_item, List<String> mDrawerList) {
        super(baseContext, list_style_navigation, list_navigation_item, mDrawerList);
        this.context = baseContext;
        listArray=mDrawerList;
    }

    @Override
    public void add(String object) {

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        LayoutInflater vi;
        vi = LayoutInflater.from(context);
        view = vi.inflate(R.layout.list_style_navigation, null);


        TextView text = (TextView) view.findViewById(R.id.list_navigation_item);
        ImageView icon = (ImageView) view.findViewById(R.id.menu_icon);

        if (position==0) {
            text.setText("Home");
            icon.setImageResource(R.drawable.ic_home_black_24dp);
        }
        else if (position==1) {
            text.setText("Follow Me");
            icon.setImageResource(R.drawable.ic_location_on_black_24dp);
        }
        else if (position==2) {
            text.setText("Information");
            icon.setImageResource(R.drawable.ic_info_outline_black_24dp);
        }
        else if (position==3) {
            text.setText("Contacts");
            icon.setImageResource(R.drawable.ic_contacts_black_24dp);
        }



        return view;
    }
}