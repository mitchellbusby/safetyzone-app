package com.safetyzone.safetyzone;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by BeckLyons on 13/10/2015.
 */


public class SafetyTipsAdapter <String> extends ArrayAdapter<String> {

    private final Context context;
    ArrayList<String> tipsArray;
    ArrayList<String> topicArray;
    ArrayList<String> idArray;
    ArrayList<String> sourceArray;



    public SafetyTipsAdapter(Context context, int resource, ArrayList<String> topic, ArrayList<String> id, ArrayList<String> tip, ArrayList<String> source) {
        super(context, resource, tip);
        this.context = context;
        tipsArray=tip;
        topicArray=topic;
        idArray=id;
        sourceArray = source;

    }
    @Override
    public void add(String object) {

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

//        if (view == null) {
        LayoutInflater vi;
        vi = LayoutInflater.from(context);
        view = vi.inflate(R.layout.list_tip_row, null);


//            if (topicArray.get(position).toString().equals("Night")) {
//                view = vi.inflate(R.layout.list_row, null);
//            }
//            else {
//                view = vi.inflate(R.layout.list_tip_row, null);
//            }

//            final Data article = getItem(position);
//
//            if (article != null) {

        TextView tipTitle = (TextView) view.findViewById(R.id.list_title);
        TextView tipInfo = (TextView) view.findViewById(R.id.list_tip);
        TextView tipSource = (TextView) view.findViewById(R.id.list_source);
        tipTitle.setText("Tip #"+ idArray.get(position).toString());
        tipInfo.setText(tipsArray.get(position).toString());
        tipSource.setText("Find out more: " + sourceArray.get(position).toString());


//            if (topicArray.get(position).toString().equals("Night")) {
//                view.setBackgroundColor(Color.CYAN);
//            }
//            else if (topicArray.get(position).toString().equals("Transport")) {
//                view.setBackgroundColor(Color.MAGENTA);
//            }
//            else if (topicArray.get(position).toString().equals("Theft")) {
//                view.setBackgroundColor(Color.LTGRAY);
//            }
//            else if (topicArray.get(position).toString().equals("Family")) {
//                view.setBackgroundColor(Color.GRAY);
//            }
//            else {
//                view.setBackgroundColor(Color.TRANSPARENT);
//            }


        return view;
    }
}